package com.springbootstarter.controller;

import com.springbootstarter.service.EventsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    private EventsService eventsService;

    public void setEventsService(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping("/ping")
    public String ping(){
        return "pong";
    }

    @RequestMapping("/countEventType")
    public String countEventType(){
        return eventsService.printEventTypeCountMap();
    }

    @RequestMapping("/countDataField")
    public String countDataField(){
        return eventsService.printDataFieldCountMap();
    }
}
