package com.example.ksw10greengg.service;

import com.example.ksw10greengg.model.GreenInfo;
import com.example.ksw10greengg.model.SummonerMatchVO;
import com.example.ksw10greengg.model.SummonerVO;

import java.util.Calendar;
import java.util.List;

public interface GreenGGService {

    // 닉네임으로 회원정보 가져오기
    SummonerVO getSummonerInfo(String id);
    // totalGames 가져오기
    List<GreenInfo> getMatchInfo(String accountId, Calendar cal);

}
