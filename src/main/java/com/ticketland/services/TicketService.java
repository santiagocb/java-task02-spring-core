package com.ticketland.services;

import com.ticketland.daos.TicketDAO;
import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.TicketType;
import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketDAO ticketDAO;

    public TicketService() {
    }

    public Ticket generateTicket(TicketType ticketType, User user, Event event) {
        return ticketDAO.createTicket(user, event, ticketType);
    }

    public void disableTicket(Event event, String ticketId) {
        ticketDAO.removeTicket(event, ticketId);
    }
}
