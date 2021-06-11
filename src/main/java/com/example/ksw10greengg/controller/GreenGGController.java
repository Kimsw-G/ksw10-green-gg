package com.example.ksw10greengg.controller;

import com.example.ksw10greengg.model.GreenInfo;
import com.example.ksw10greengg.model.SummonerVO;
import com.example.ksw10greengg.service.GreenGGService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        System.out.println(id+"를 검색했습니다.");
        return REDIRECT+GREEN+"?accountId="+vo.getAccountId();
    }

    @GetMapping("/green")
    public String searchMatch(String accountId, Model model){
        Calendar cal = Calendar.getInstance();
        model.addAttribute("dateKey",(7-cal.get(Calendar.DAY_OF_WEEK)));
        List<GreenInfo> list = new ArrayList<>();
        list = greenGGService.getMatchInfo(accountId,cal);

        model.addAttribute("dates",new String[]{"일","월","화","수","목","금","토"});
        model.addAttribute("list",list);
        return GREEN;
    }
}
