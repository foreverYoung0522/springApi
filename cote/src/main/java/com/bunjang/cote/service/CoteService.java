package com.bunjang.cote.service;


import com.bunjang.cote.dao.EventDAO;
import com.bunjang.cote.model.Event;
import com.bunjang.cote.model.EventSummary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class CoteService {

    @Autowired
    EventDAO eventDao;



    public JSONArray getData(){


        String baseUrl = "https://s3.ap-northeast-2.amazonaws.com/bunjang-interview/events.json";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity("parameters",headers);
        URI url = URI.create(baseUrl);

        ResponseEntity<JSONArray> response = restTemplate.exchange(url, HttpMethod.GET,entity,JSONArray.class);

        JSONArray eventArray = null;
        try {
            JSONParser jsonParser = new JSONParser();
            eventArray = new JSONArray();
            eventArray = response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return eventArray;
    }

    public List<Event> putData(){
        Gson gson = new Gson();
        eventDao = new EventDAO();
        List<Event> eventList;
        eventList = gson.fromJson(this.getData().toString(),new TypeToken<List<Event>>(){}.getType());
        eventDao.setEvents(eventList);
        return eventDao.getEvents();
    }


    public JSONArray problem1(){
        return this.getData();
    }

    public JSONArray problem2(){
        List<Event> events = this.putData();

        JSONArray jsonArray = new JSONArray();
        List<EventSummary> summaries  =eventDao.readMemberSummaries();

        for(int i=0;i<summaries.size(); i++){
            jsonArray.add(summaries.get(i).getJSONObject());
        }
        return jsonArray;
    }

    public JSONArray problem3(String sort){
        List<Event> events = this.putData();

        JSONArray jsonArray = new JSONArray();
        List<EventSummary> summaries;
        if(sort==null){
            summaries  =eventDao.sortSummaries("TotalEvent");
        }
        else{
            summaries  =eventDao.sortSummaries(sort);
        }
        for(int i=0;i<summaries.size(); i++){
            jsonArray.add(summaries.get(i).getJSONObject());
        }
        return jsonArray;

    }


}
