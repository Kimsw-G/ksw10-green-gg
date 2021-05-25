package com.example.ksw10greengg.service;

import com.example.ksw10greengg.model.SummonerMatchVO;
import com.example.ksw10greengg.model.SummonerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public SummonerMatchVO getMatchInfo(String accountId,long beginTime, long endTime) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpEntity<SummonerMatchVO> httpEntity = setHeaders();
        // TODO :SET BEGIN,END TIME
        ResponseEntity<SummonerMatchVO> responseEntity = restTemplate.exchange(SEARCH_MATCHES_INFO+accountId+BEGIN_TIME+beginTime+END_TIME+endTime, HttpMethod.GET,httpEntity,SummonerMatchVO.class);

        return responseEntity.getBody();
    }
}
