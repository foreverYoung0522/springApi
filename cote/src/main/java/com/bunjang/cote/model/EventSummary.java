package com.bunjang.cote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EventSummary {

    @JsonProperty("login")
    private String login;
    @JsonProperty("events")
    private Map<String,Integer> events;

    public EventSummary(String login){
        this.login = login;
        this.events = null;
    }
    public EventSummary(String login, Map<String,Integer> events){
        this.login = login;
        this.events = events;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Map<String, Integer> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Integer> events) {
        this.events = events;
    }
    public Object getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("login",this.login);
        obj.put("events",this.events);

        return obj;
    }
}
