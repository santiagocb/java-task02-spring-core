package com.ticketland.daos;

import com.ticketland.entities.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventDAO {

    private final List<Event> events;

    public EventDAO() {
        events = new ArrayList<>();
    }

    public void createEvent(Event event) {
        events.add(event);
    }

    public Optional<Event> getEventById(String eventId) {
        return events.stream().filter(e -> e.id().equals(eventId)).findFirst();
    }

    public List<Event> getEvents() {
        return events;
    }
}
