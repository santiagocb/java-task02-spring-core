package com.ticketland.services;

import com.ticketland.daos.TicketDAO;
import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.TicketType;
import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    public Ticket generateTicket(TicketType ticketType, User user, Event event) {
        return ticketDAO.createTicket(user, event, ticketType);
    }

    public void disableTicket(Event event, String ticketId) {
        ticketDAO.removeTicket(event, ticketId);
    }

    public Ticket getTicketByEvent(Event event, String ticketId) {
        return ticketDAO.getTicketByEvent(event, ticketId).orElseThrow();
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        return ticketDAO.getTicketsByEvent(event);
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }
}
