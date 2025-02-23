package com.sudagoarth._Auth.controllers;

import com.sudagoarth._Auth.models.Event;
import com.sudagoarth._Auth.services.EventService;
import com.sudagoarth._Auth.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Endpoint to get all events
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        ApiResponse response = new ApiResponse("success", 200, events, "Events 01", true, null, null);
        return ResponseEntity.ok(response);
    }

    // Endpoint to get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            ApiResponse response = new ApiResponse("success", 200, event.get(), "Event 01", false, null, null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("not found", 404, null, "Event Not Found", false, null, null);
            return ResponseEntity.status(404).body(response);
        }
    }

    // Endpoint to create a new event
    @PostMapping
    public ResponseEntity<ApiResponse> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        ApiResponse response = new ApiResponse("success", 201, createdEvent, "Event Created", false, null, null);
        return ResponseEntity.status(201).body(response);
    }

    // Endpoint to update an event
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        if (updatedEvent != null) {
            ApiResponse response = new ApiResponse("success", 200, updatedEvent, "Event Updated", false, null, null);
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse("not found", 404, null, "Event Not Found", false, null, null);
            return ResponseEntity.status(404).body(response);
        }
    }

    // Endpoint to delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        ApiResponse response = new ApiResponse("success", 200, null, "Event Deleted", false, null, null);
        return ResponseEntity.ok(response);
    }
}
