package com.ticketland.programs;

import com.ticketland.entities.TicketType;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements BookingFacade {

    public static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    public BookingService(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;

        logger.debug("Booking service created");
    }

    @Override
    public String createBooking(String eventId, String userId, TicketType ticketType) {
        var event = eventService.getEventById(eventId);
        var user = userService.getUserById(userId);
        var ticket = ticketService.generateTicket(ticketType, user, event);

        logger.info(String.format("Ticket purchased [%s] ::: [%s]%s has 1 %s entry to %s in %s on %s",
                ticket.id(), user.id(), user.name(), ticketType, event.name(), event.place(), event.date().toString()));

        return ticket.id();
    }

    @Override
    public void cancelBooking(String eventId, String ticketId) {
        var event = eventService.getEventById(eventId);

        logger.info(String.format("Canceling ticket %s: Entry for %s was removed from the system%n", ticketId, event.name()));

        ticketService.disableTicket(event, ticketId);
    }

    @Override
    public void showTicketById(String eventId, String ticketId) {
        var event = eventService.getEventById(eventId);
        var ticket = ticketService.getTicketByEvent(event, ticketId);

        logger.info(String.format("Ticket %s information: %s%n", ticketId, ticket));
    }

    @Override
    public void showAllTicketsByEvent(String eventId) {
        var event = eventService.getEventById(eventId);

        logger.info(String.format("All the tickets for [%s] %s:", event.id(), event.name()));
        ticketService.getTicketsByEvent(event).forEach(t -> logger.info(t.toString()));
    }
}
