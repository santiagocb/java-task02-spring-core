package com.ticketland.daos;

import com.ticketland.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventDAO {

    @Autowired
    private List<Event> events;

    public EventDAO() {
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
