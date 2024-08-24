package com.ticketland.daos;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.TicketType;
import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketDAO {

    private HashMap<Event, List<Ticket>> tickets;

    private TicketDAO() {
    }

    @Autowired
    void setTickets(HashMap<Event, List<Ticket>> tickets) {
        this.tickets = tickets;
    }

    public Ticket createTicket(User user, Event event, TicketType ticketType) {
        Ticket ticket = new Ticket(generateRandomTicketNumber(), ticketType, user);
        if (tickets.containsKey(event)){
            List<Ticket> ticketList = tickets.get(event);
            ticketList.add(ticket);
        } else {
            List<Ticket> ticketList = new ArrayList<>();
            ticketList.add(ticket);
            tickets.put(event, ticketList);
        }
        return ticket;
    }

    public void removeTicket(Event event, String ticketId) {
        var ticket = tickets.get(event).stream().filter(t -> t.id().equals(ticketId)).findAny();
        ticket.ifPresent(t -> tickets.get(event).remove(t));
    }

    public Optional<Ticket> getTicketByEvent(Event event, String ticketId) {
        return tickets.get(event).stream().filter(ticket -> ticket.id().equals(ticketId)).findFirst();
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        return tickets.get(event);
    }

    private String generateRandomTicketNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
