package com.bunjang.cote.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import org.json.simple.JSONObject;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Event {
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("actor")
    private Actor actor;


    private Map<String,Object> event;

    public Event(){
    }

    public Event(String id, String type,Actor actor){
        this.id = id;
        this.type = type;
        this.actor = actor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Object getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id",this.id);
        obj.put("type",this.type);
        obj.put("actor",this.actor);

        return obj;
    }
}
