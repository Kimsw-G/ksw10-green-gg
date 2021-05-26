package com.example.ksw10greengg.controller;

import com.example.ksw10greengg.model.SummonerMatchVO;
import com.example.ksw10greengg.model.SummonerVO;
import com.example.ksw10greengg.service.GreenGGService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        //TODO : 쿼리스트링을 통해 id값 날려주기!
        return REDIRECT+GREEN+"?accountId="+vo.getAccountId();
    }

    @GetMapping("/green")
    public String searchMatch(String accountId, Model model){
        Calendar cal = Calendar.getInstance();
        model.addAttribute("dateKey",(cal.get(Calendar.DAY_OF_WEEK)));
        List<Integer> list = new ArrayList<>();
//        for(int i=0; i<180;i++){
//            list.add(0,greenGGService.getMatchInfo(accountId,cal));
//            cal.add(Calendar.DAY_OF_MONTH,-2);
//        }
        list = greenGGService.getMatchInfo(accountId,cal);

        System.out.println("나와따");
        model.addAttribute("dates",new String[]{"월","화","수","목","금","토","일"});
        model.addAttribute("list",list);
        return GREEN;
    }
}
