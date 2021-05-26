package com.example.ksw10greengg.service;

import com.example.ksw10greengg.model.SummonerMatchVO;
import com.example.ksw10greengg.model.SummonerVO;

import java.util.Calendar;
import java.util.List;

public interface GreenGGService {

    // 무어시 피료하까요
    
    // 닉네임으로 회원정보 가져오기
    SummonerVO getSummonerInfo(String id);
    // 회원정보로 판수 가져오기
    // 날짜별로 가져와서
    // 180개 가져온다 (반년)
    // totalGames 가져온다
    List<Integer> getMatchInfo(String accountId, Calendar cal);

}
