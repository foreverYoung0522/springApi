package com.bunjang.cote.controller;
import com.bunjang.cote.service.CoteService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CoteController {

    @Autowired
    private CoteService problem1service;

    @GetMapping("/events")
    public JSONArray problem1(){
        return problem1service.problem1();
    }
    @GetMapping("/events/summaries")
    public JSONArray problem2(){
        return problem1service.problem2();
    }

    @GetMapping("/event-summaries")
    public JSONArray problem3(String sort){
        return problem1service.problem3(sort);
    }
}
