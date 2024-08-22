package com.ticketland.programs;

import com.ticketland.entities.TicketType;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements BookingFacade {

    UserService userService;
    EventService eventService;
    TicketService ticketService;

    public BookingService(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public String createBooking(String eventId, String userId, TicketType ticketType) {
        var event = eventService.getEventById(eventId).orElseThrow();
        var user = userService.getUserById(userId).orElseThrow();
        return ticketService.generateTicket(ticketType, user, event).id();
    }

    @Override
    public void cancelBooking(String eventId, String ticketId) {
        var event = eventService.getEventById(eventId).orElseThrow();
        ticketService.disableTicket(event, ticketId);
    }
}
