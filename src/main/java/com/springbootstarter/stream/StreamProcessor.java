package com.springbootstarter.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootstarter.config.ConfigProperties;
import com.springbootstarter.model.Event;
import com.springbootstarter.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class StreamProcessor {
    @Autowired
    ConfigProperties configurationProperties;

    private EventsService eventsService;
    private String streamLocation;

    ObjectMapper mapper = new ObjectMapper();


    public void setEventsService(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    public void  setStreamLocation(String streamLocation){
        this.streamLocation = streamLocation;
    }

    public void execute() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(streamLocation);
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
        consumeInputStream(process, event -> {
             eventsService.countDataField(event.getData());
            eventsService.incrementEventType(event.getEvent_type());
        });

    }

    private void consumeInputStream(final Process process, Consumer<Event> eventConsumer) {
        new Thread(new Runnable() {
            public void run() {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                try {
                    while ((line = br.readLine()) != null) {
                        //System.out.println("[OUT] " + line);
                        try {
                                Event event = mapper.readValue(line, Event.class);
                                eventConsumer.accept(event);
                        } catch (Exception e) {
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
