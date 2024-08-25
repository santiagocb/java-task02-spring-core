package com.ticketland.persistence;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import com.ticketland.persistence.util.impl.CSVEventDataReader;
import com.ticketland.persistence.util.impl.CSVUserDataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
public class Storage {

    private final List<Event> events;

    private final List<User> users;

    private final HashMap<Event, List<Ticket>> tickets;

    @Autowired
    CSVUserDataReader csvUserDataReader;

    @Autowired
    CSVEventDataReader csvEventDataReader;

    public Storage() {
        events = new ArrayList<>();
        users = new ArrayList<>();
        tickets = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        users.addAll(csvUserDataReader.getDataFromCSV());
        events.addAll(csvEventDataReader.getDataFromCSV());
    }

    @Bean("users")
    public List<User> getUsers() {
        return users;
    }

    @Bean("events")
    public List<Event> getEvents() {
        return events;
    }

    @Bean("tickets")
    public HashMap<Event, List<Ticket>> getTickets() {
        return tickets;
    }

}
