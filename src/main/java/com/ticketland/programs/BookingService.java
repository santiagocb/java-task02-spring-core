package com.ticketland.programs;

import com.ticketland.entities.TicketType;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements BookingFacade {


    private final String SEPARATOR = "–––––––––––––––––––––";

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
        var event = eventService.getEventById(eventId);
        var user = userService.getUserById(userId);
        var ticket = ticketService.generateTicket(ticketType, user, event);
        System.out.printf("Ticket %s purchased: [%s]%s has 1 entry to %s in %s on %s%n",
                ticket.id(), user.id(), user.name(), event.name(), event.place(), event.date().toString());
        System.out.println(SEPARATOR);
        return ticket.id();
    }

    @Override
    public void cancelBooking(String eventId, String ticketId) {
        var event = eventService.getEventById(eventId);
        System.out.printf("Canceling ticket %s: Entry for %s was removed from the system%n", ticketId, event.name());
        System.out.println(SEPARATOR);
        ticketService.disableTicket(event, ticketId);
    }

    @Override
    public void showTicketById(String eventId, String ticketId) {
        var event = eventService.getEventById(eventId);
        var ticket = ticketService.getTicketByEvent(event, ticketId);
        System.out.printf("Ticket %s information: %s%n", ticketId, ticket);
        System.out.println(SEPARATOR);
    }

    @Override
    public void showAllTicketsByEvent(String eventId) {
        var event = eventService.getEventById(eventId);
        System.out.printf("All the tickets for [%s] %s: %n", event.id(), event.name());
        ticketService.getTicketsByEvent(event).forEach(System.out::println);
    }
}
