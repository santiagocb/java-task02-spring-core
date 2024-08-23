package com.ticketland.services;

import com.ticketland.daos.EventDAO;
import com.ticketland.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventDAO eventDAO;

    public void registerEvent(Event event) {
        eventDAO.createEvent(event);
    }

    public Event getEventById(String eventId) {
        return eventDAO.getEventById(eventId).orElseThrow();
    }

    public void showAllEvents() {
        System.out.println("All available events:");
        eventDAO.getEvents().forEach(System.out::println);
    }

}
