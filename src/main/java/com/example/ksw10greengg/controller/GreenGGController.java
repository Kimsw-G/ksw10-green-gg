package com.example.ksw10greengg.controller;

import com.example.ksw10greengg.model.SummonerVO;
import com.example.ksw10greengg.service.GreenGGService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreenGGController extends ControllerUtil{


    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    GreenGGService greenGGService;

    @GetMapping("/")
    public String viewMain(){
        return MAIN;
    }

    @PostMapping("/")
    public String searchId(String id){
        SummonerVO vo = greenGGService.getSummonerInfo(id);
        //TODO : 쿼리스트링을 통해 id값 날려주기!
        return REDIRECT+GREEN+"?accountId="+vo.getAccountId();
    }

    @GetMapping("/green")
    public String searchId(String accountId){


        return GREEN;
    }
}
