package com.ticketland.daos;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.TicketType;
import com.ticketland.entities.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketDAO {

    private final HashMap<Event, List<Ticket>> tickets;

    private TicketDAO() {
        tickets = new HashMap<>();
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

    private String generateRandomTicketNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
