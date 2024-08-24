package com.ticketland.services;

import com.ticketland.daos.EventDAO;
import com.ticketland.entities.Event;
import com.ticketland.programs.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    public static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    EventDAO eventDAO;

    public void registerEvent(Event event) {
        eventDAO.createEvent(event);
    }

    public Event getEventById(String eventId) {
        return eventDAO.getEventById(eventId).orElseThrow();
    }

    public void showAllEvents() {
        logger.info("All available events:");

        eventDAO.getEvents().forEach(u -> logger.info(u.toString()));
    }

}
