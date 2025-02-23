package com.sudagoarth._Auth.services;

import com.sudagoarth._Auth.models.Event;
import com.sudagoarth._Auth.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get an event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Update an existing event
    public Event updateEvent(Long id, Event eventDetails) {
        if (eventRepository.existsById(id)) {
            eventDetails.setId(id);
            return eventRepository.save(eventDetails);
        } else {
            return null; // Or throw an exception if needed
        }
    }

    // Delete an event by ID
    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        }
    }
}
