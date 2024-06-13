//package com.vinicius_santos.todolist.controller;
//
//import com.google.api.client.util.DateTime;
//import com.google.api.services.calendar.model.Event;
//import com.google.api.services.calendar.model.EventDateTime;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//@RequiredArgsConstructor
//public class EventController {
//
//    private com.google.api.services.calendar.Calendar client;
//
//    public EventController(com.google.api.services.calendar.Calendar client) {
//        this.client = client;
//    }
//
//    @PostMapping("/createEvent")
//    public String createEvent(@RequestParam String summary, @RequestParam String location,
//                              @RequestParam String description, @RequestParam String startDateTime,
//                              @RequestParam String endDateTime) {
//        try {
//            DateTime start = new DateTime(startDateTime);
//            DateTime end = new DateTime(endDateTime);
//            Event event = createEvent(summary, location, description, start, end);
//            return "Event created with ID: " + event.getId();
//        } catch (IOException e) {
//            return "Error creating event: " + e.getMessage();
//        }
//    }
//
//    public Event createEvent(String summary, String location, String description, DateTime startDateTime, DateTime endDateTime) throws IOException {
//        Event event = new Event()
//            .setSummary(summary)
//            .setLocation(location)
//            .setDescription(description);
//
//        EventDateTime start = new EventDateTime()
//            .setDateTime(startDateTime)
//            .setTimeZone("America/Sao_Paulo");
//        event.setStart(start);
//
//        EventDateTime end = new EventDateTime()
//            .setDateTime(endDateTime)
//            .setTimeZone("America/Sao_Paulo");
//        event.setEnd(end);
//
//        String calendarId = "primary";
//        event = client.events().insert(calendarId, event).execute();
//        System.out.printf("Event created: %s\n", event.getHtmlLink());
//
//        return event;
//    }
//}