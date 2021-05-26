package com.example.ksw10greengg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SummonerMatchVO {
    private int totalGames;
    private List<MatchReferenceVO> matches;

    private int dateImsi;
}
