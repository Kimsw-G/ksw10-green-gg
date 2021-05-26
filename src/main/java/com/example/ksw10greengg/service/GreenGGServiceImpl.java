package com.example.ksw10greengg.service;

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
import java.util.List;

@Service
public class GreenGGServiceImpl implements GreenGGService{

    //TODO : Logger는 뭐하는 친구지?

    // 기본 정보들
    private static final String RIOTGAMES_URL = "https://developer.riotgames.com";

    private static final String DEFAULT_PATH = "https://kr.api.riotgames.com";

    private static final String SEARCH_SUMMONER_INFO = DEFAULT_PATH + "/lol/summoner/v4/summoners/by-name/";
    private static final String SEARCH_MATCHES_INFO = DEFAULT_PATH +"/lol/match/v4/matchlists/by-account/";
    private static final String BEGIN_TIME = "?beginTime=";
    private static final String END_TIME = "&endTime=";

    private static final String X_Riot_Token = "X-Riot-Token";

    private static final String API_KEY = "RGAPI-a0e74f97-2b7b-4fac-83ec-b2588f2cb46b";

    // TODO : ???
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
        // TODO : ?????2
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<SummonerVO> httpEntity = setHeaders();
        ResponseEntity<SummonerVO> responseEntity = restTemplate.exchange(SEARCH_SUMMONER_INFO+id, HttpMethod.GET,httpEntity,SummonerVO.class);

        //
        return responseEntity.getBody();
    }

    @Override
    public List<Integer> getMatchInfo(String accountId, Calendar cal) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<SummonerMatchVO> responseEntity = null;
        long endTime = CalcEndTimeMills(cal);
        long beginTime = CalcBeginTimeMillis(cal);


        HttpEntity<SummonerMatchVO> httpEntity = setHeaders();
        // TODO :SET BEGIN,END TIME
        try {
             responseEntity = restTemplate.exchange(SEARCH_MATCHES_INFO+accountId+BEGIN_TIME+beginTime+END_TIME+endTime, HttpMethod.GET,httpEntity,SummonerMatchVO.class);
        }catch (Exception e){
            // 반년간 게임 기록이 없는 사람일때
        }

        return cutByDay(responseEntity.getBody());
    }

    private List<Integer> cutByDay(SummonerMatchVO param){
        List<Integer> list = new ArrayList<>();

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH,-180);
        long startTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_MONTH,+1);
        long endTime = cal.getTimeInMillis();

        Integer value = 0;
        for (MatchReferenceVO vo : param.getMatches()) { // foreach 말고 for문으로 바꾸자
            // Timestamp를 180일 전부터 검사한다
            // 해당하지 않을시, 날짜를 하루+1 한 다음 다시 검사한다
            // 해당할시 value값에 +1을 해준다.
            if(startTime < vo.getTimestamp() && endTime > vo1.getTimestamp()){
                value++;
            }else {
                startTime += 86400000;
                endTime += 86400000;
            }


        }

        return list;
    }

    private long CalcBeginTimeMillis(Calendar cal){
        cal.add(Calendar.DAY_OF_MONTH,-180);
        return cal.getTimeInMillis();
    }
    private long CalcEndTimeMills(Calendar cal){
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTimeInMillis();
    }
}
