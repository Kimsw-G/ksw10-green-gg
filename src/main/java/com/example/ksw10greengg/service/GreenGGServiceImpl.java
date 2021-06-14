package com.example.ksw10greengg.service;

import com.example.ksw10greengg.model.GreenInfo;
import com.example.ksw10greengg.model.MatchReferenceVO;
import com.example.ksw10greengg.model.SummonerMatchVO;
import com.example.ksw10greengg.model.SummonerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class GreenGGServiceImpl implements GreenGGService{

    // 기본 정보들
    private static final String RIOTGAMES_URL = "https://developer.riotgames.com";

    private static final String DEFAULT_PATH = "https://kr.api.riotgames.com";

    private static final String SEARCH_SUMMONER_INFO = DEFAULT_PATH + "/lol/summoner/v4/summoners/by-name/";
    private static final String SEARCH_MATCHES_INFO = DEFAULT_PATH +"/lol/match/v4/matchlists/by-account/";
    private static final String BEGIN_TIME = "?beginTime=";
    private static final String END_TIME = "&endTime=";
    private static final String BEGIN_INDEX = "&beginIndex=";

    private static final String X_Riot_Token = "X-Riot-Token";

    private static final String API_KEY = "";

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public static <T> HttpEntity<T> setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ORIGIN, RIOTGAMES_URL);
        headers.set(X_Riot_Token, API_KEY);

        return new HttpEntity<T>(headers);
    }

    @Override
    public SummonerVO getSummonerInfo(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<SummonerVO> httpEntity = setHeaders();
        ResponseEntity<SummonerVO> responseEntity
                = restTemplate.exchange(SEARCH_SUMMONER_INFO+id, HttpMethod.GET,httpEntity,SummonerVO.class);

        return responseEntity.getBody();
    }

    @Override
    public List<GreenInfo> getMatchInfo(String accountId, Calendar cal) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        long beginTime = CalcBeginTimeMillis(cal);
        long beginIndex = 0;
        HttpEntity<SummonerMatchVO> httpEntity = setHeaders();
        ResponseEntity<SummonerMatchVO> responseEntity = null;
        List<MatchReferenceVO> list = new ArrayList<>();


        while (true) {
            responseEntity = restTemplate.exchange(SEARCH_MATCHES_INFO + accountId + BEGIN_TIME + beginTime + BEGIN_INDEX + beginIndex , HttpMethod.GET , httpEntity , SummonerMatchVO.class);
            if (responseEntity.getBody().getMatches().size() == 0) break;
            list.addAll(responseEntity.getBody().getMatches());
            beginIndex+=100;
        }
        Collections.reverse(list);
        //list로 가져옴. 이걸 이어붙인 리스트가 필요함
        return cutByDay(list);
    }

    private List<GreenInfo> cutByDay(List<MatchReferenceVO> param){
        List<GreenInfo> list = new ArrayList<>();

        Calendar today = Calendar.getInstance();

        long startTime = CalcBeginTimeMillis(today);
        today.add(Calendar.DAY_OF_MONTH,+1);
        long endTime = today.getTimeInMillis();


        int matchCnt = 0;
        int key = 0;
        boolean flag = true;
        while (true) {
            if(flag&&startTime <= param.get(key).getTimestamp() && endTime > param.get(key).getTimestamp()){
                matchCnt++;
                key++;
                if(key==param.size()) flag = false;
            }else {
                Calendar cal = Calendar.getInstance();
                GreenInfo gInfo = new GreenInfo();
                cal.setTimeInMillis(endTime);
                gInfo.setMatchCnt(matchCnt);
                gInfo.setYear(cal.get(Calendar.YEAR));
                gInfo.setMonth(cal.get(Calendar.MONTH)+1);
                gInfo.setDay(cal.get(Calendar.DAY_OF_MONTH));

                list.add(gInfo);
                startTime += 86400000;
                endTime += 86400000;
                matchCnt=0;
            }
            Calendar imsiCal = Calendar.getInstance();
            imsiCal.setTimeInMillis(startTime);
            if (Calendar.getInstance().getTimeInMillis()<startTime) {
                list.remove(list.size()-1);
                break;
            }
        }

        return list;
    }

    private long CalcBeginTimeMillis(Calendar cal){
        cal.add(Calendar.DAY_OF_MONTH,-180);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTimeInMillis();
    }
}
