package com.ticketland.services;

import com.ticketland.entities.*;
import com.ticketland.programs.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @Autowired
    @InjectMocks
    BookingService bookingService;

    @Test
    public void createABooking() {
        // Given
        var event = new Event("eventId1", "eventName1", "placeTest1", new Location(0, 0), LocalDate.now());
        var user = new User("userId1", "userName1", "email1.com");

        var expectedTicket = new Ticket("ticketId1", TicketType.VIP, user);

        MockitoAnnotations.openMocks(this);

        when(eventService.getEventById(anyString())).thenReturn(event);
        when(userService.getUserById(anyString())).thenReturn(user);
        when(ticketService.generateTicket(TicketType.VIP, user, event)).thenReturn(expectedTicket);

        // When
        var result = bookingService.createBooking(event.id(), user.id(), TicketType.VIP);

        // Then
        assertEquals(result, expectedTicket.id());
    }
}
