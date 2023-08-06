package com.example.randoliravindu.controller;


import com.example.randoliravindu.model.Event;
import com.example.randoliravindu.model.Payload;
import com.example.randoliravindu.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) {
        Optional<Event> optionalEvent = Optional.ofNullable(eventService.getEventById(id));
        return optionalEvent.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, @RequestBody Event event) {
        if (!eventService.existsEventById(id)) {
            return ResponseEntity.notFound().build();
        }
        event.setEventId(id);
        Event updatedEvent = eventService.saveEvent(event);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        if (!eventService.existsEventById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveBatchData")
    public ResponseEntity<String> saveBatchData(@RequestBody Payload payload) {
        try {
            eventService.saveBatchData(payload);
            return ResponseEntity.ok("Batch data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save batch data.");
        }
    }
}