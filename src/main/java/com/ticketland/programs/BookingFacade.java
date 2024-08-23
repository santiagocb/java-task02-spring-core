package com.ticketland.programs;

import com.ticketland.entities.TicketType;

public interface BookingFacade {

    String createBooking(String eventId, String userId, TicketType ticketType);

    void cancelBooking(String eventId, String ticketId);

    void showTicketById(String eventId, String ticketId);

    void showAllTicketsByEvent(String eventId);
}
