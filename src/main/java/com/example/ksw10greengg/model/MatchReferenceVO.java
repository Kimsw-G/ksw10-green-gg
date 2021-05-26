package com.example.ksw10greengg.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchReferenceVO {
    private long gameId;
    private String role;
    private int season;
    private String platformId;
    private int champion;
    private int queue;
    private String lane;
    private long timestamp;
}
