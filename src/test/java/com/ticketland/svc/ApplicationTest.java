package com.ticketland.svc;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.TicketType;
import com.ticketland.entities.User;
import com.ticketland.persistence.Storage;
import com.ticketland.programs.BookingService;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationTest {

    @Test
    void shouldValidatePostConstructInitialization() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.ticketland.persistence");
        context.refresh();

        Storage storage = context.getBean(Storage.class);

        List<User> users = storage.getUsers();
        List<Event> events = storage.getEvents();

        assertEquals(2, users.size());
        assertEquals(4, events.size());
    }

    @Test
    void shouldMassiveCreateBookings() {
        // Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.ticketland");
        context.refresh();

        TicketService ticketService = context.getBean(TicketService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        // When
        bookingService.createBooking("001", "TestId1", TicketType.VIP);
        bookingService.createBooking("001", "TestId1", TicketType.VIP);
        bookingService.createBooking("002", "TestId1", TicketType.VIP);
        bookingService.createBooking("002", "TestId2", TicketType.GENERAL);
        bookingService.createBooking("002", "TestId2", TicketType.GENERAL);
        bookingService.createBooking("003", "TestId2", TicketType.GENERAL);

        Storage storage = context.getBean(Storage.class);

        // Then
        assertEquals(2, storage.getUsers().size());
        assertEquals(3, storage.getTickets().size());
        assertEquals(6, ticketService.getAllTickets().size());

    }

    @Test
    void verifyANewBooking() {
        // Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.ticketland");
        context.refresh();

        TicketService ticketService = context.getBean(TicketService.class);
        EventService eventService = context.getBean(EventService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        // When
        String ticketId = bookingService.createBooking("001", "TestId1", TicketType.VIP);

        Storage storage = context.getBean(Storage.class);

        Ticket expectedTicket = ticketService.getTicketByEvent(eventService.getEventById("001"), ticketId);

        // Then
        assertEquals(1, storage.getTickets().size());
        assertEquals(expectedTicket.id(), ticketId);
        assertEquals(expectedTicket.user().id(), "TestId1");
        assertEquals(expectedTicket.type(), TicketType.VIP);
    }

    @Test
    void cancelABooking() {
        // Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.scan("com.ticketland");
        context.refresh();

        TicketService ticketService = context.getBean(TicketService.class);
        EventService eventService = context.getBean(EventService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        // When
        String ticketId = bookingService.createBooking("001", "TestId1", TicketType.VIP);
        bookingService.cancelBooking("001", ticketId);

        // Then
        assertEquals(0, ticketService.getAllTickets().size());
        assertThrows(NoSuchElementException.class, () -> ticketService.getTicketByEvent(eventService.getEventById("001"), ticketId));
    }
}
