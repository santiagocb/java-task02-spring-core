package com.ticketland.services;

import com.ticketland.daos.EventDAO;
import com.ticketland.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventDAO eventDAO;

    public EventService() {
    }

    public void registerEvent(Event event) {
        eventDAO.createEvent(event);
    }

    public Optional<Event> getEventById(String eventId) {
        return eventDAO.getEventById(eventId);
    }

}
