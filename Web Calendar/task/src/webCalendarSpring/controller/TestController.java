package webCalendarSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.model.Event;
import webCalendarSpring.service.EventService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<Object> addEvent(@RequestBody Event event) {
        if (event.getEvent() == null || event.getEvent().trim().isEmpty() || event.getDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        eventService.addEvent(event);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "The event has been added!");
        responseBody.put("event", event.getEvent());
        responseBody.put("date", event.getDate().toString());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(value = "start_time", required = false) String startTime,
            @RequestParam(value = "end_time", required = false) String endTime) {

        List<Event> eventList;

        if (startTime != null && endTime != null) {
            LocalDate startDate = LocalDate.parse(startTime);
            LocalDate endDate = LocalDate.parse(endTime);
            eventList = eventService.findEventsBetweenDates(startDate, endDate);
        } else {
            eventList = eventService.getEvent();
        }

        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }


    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> getEventByDate() {
        List<Event> events = eventService.findByDate(LocalDate.now());
        if (!events.isEmpty()) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        return new ResponseEntity<>(List.of(), HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEventById(@PathVariable("id") Long id) {
        Optional<Event> event = eventService.findById(id);
        if (event.isEmpty()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "The event doesn't exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event.get(), HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable("id") Long id) {
        Optional<Event> event = eventService.findById(id);
        if (event.isEmpty()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "The event doesn't exist!");
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }

        Event deletedEvent = event.get();
        eventService.deleteById(id);

        // Return the deleted event object instead of a message
        return new ResponseEntity<>(deletedEvent, HttpStatus.OK);
    }

}
