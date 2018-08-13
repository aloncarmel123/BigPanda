package com.springbootstarter.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventsService {

    private ConcurrentHashMap<String,AtomicInteger> eventTypeCounterMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,AtomicInteger> dataFieldCounterMap = new ConcurrentHashMap<>();

    public void incrementEventType(String eventType){
        eventTypeCounterMap.computeIfAbsent(eventType,k-> new AtomicInteger(0)).incrementAndGet();
    }

    public void countDataField(String data){
        String[] fields = data.split("\\s+");
        for( String field : fields) {
            dataFieldCounterMap.computeIfAbsent(field, k -> new AtomicInteger(0)).incrementAndGet();
        }
    }

    public String printEventTypeCountMap() {
        StringBuilder sb = new StringBuilder();
        for (String dataField: eventTypeCounterMap.keySet()){

            String key = dataField.toString();
            String value = eventTypeCounterMap.get(dataField).toString();
            sb.append(key + "->" + value);
            sb.append("\r");
        }
        return sb.toString();
    }

    public String printDataFieldCountMap() {
        StringBuilder sb = new StringBuilder();
        for (String dataField: dataFieldCounterMap.keySet()){

            String key = dataField.toString();
            String value = dataFieldCounterMap.get(dataField).toString();
            sb.append(key + "->" + value);
            sb.append("\n");
        }
        return sb.toString();
    }
}
