package com.bunjang.cote.dao;

import com.bunjang.cote.model.Event;
import com.bunjang.cote.model.EventSummary;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class EventDAO {

    private List<Event> events = new ArrayList<>();

    public void setEvents(List<Event> data){
        events = data;
    }
    public List getEvents(){
        return events;
    }

    public List<EventSummary> readMemberSummaries(){
        List<EventSummary> summaries = new ArrayList<>();
        String login;
        Map<String,List<String>> map = new LinkedHashMap<>();

        for(int i=0;i<events.size();i++){

            login = events.get(i).getActor().getLogin();

            if(map.containsKey(login)){
                map.get(login).add(events.get(i).getType());
            }
            else{
                List<String> arr = new ArrayList<>();
                arr.add(events.get(i).getType());
                map.put(login,arr);
                EventSummary summary = new EventSummary(login);
                summaries.add(summary);
            }

        }


        for(int i=0; i<summaries.size();i++){
            int total=0;
            Map<String,Integer> countmap = new LinkedHashMap<>();
            for(int j=0;j<map.get(summaries.get(i).getLogin()).size();j++){
                if(countmap.containsKey(map.get(summaries.get(i).getLogin()).get(j))){
                    total++;
                    countmap.put("TotalEvent",total);

                    int count = countmap.get(map.get(summaries.get(i).getLogin()).get(j));
                    countmap.put(map.get(summaries.get(i).getLogin()).get(j),count+1);
                }
                else{
                    total++;
                    countmap.put("TotalEvent",total);
                    countmap.put(map.get(summaries.get(i).getLogin()).get(j),1);
                }

            }
            summaries.get(i).setEvents(countmap);

        }
        return summaries;
    }


    public List sortSummaries(String sort){
        List<EventSummary> summaries = this.readMemberSummaries();
        Map<String,Integer> counts = new HashMap<>();

        List<EventSummary> sortedsummaries = new ArrayList<>();

        for(int i=0;i<summaries.size();i++){
            if(summaries.get(i).getEvents().get(sort)==null){
                counts.put(summaries.get(i).getLogin(),0);
            }
            else{
                counts.put(summaries.get(i).getLogin(),summaries.get(i).getEvents().get(sort));
            }

        }

        List<Map.Entry<String,Integer>> entryList = new LinkedList<>(counts.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });


        for(Map.Entry<String,Integer> entry : entryList){

            for(int j = 0; j< summaries.size();j++){
                if(summaries.get(j).getLogin().equals(entry.getKey())){
                    sortedsummaries.add(summaries.get(j));


                    break;
                }
            }
        }



        return sortedsummaries;

    }

}
