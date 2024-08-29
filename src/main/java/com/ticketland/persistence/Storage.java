package com.ticketland.persistence;

import com.ticketland.persistence.util.impl.CSVEventDataReader;
import com.ticketland.persistence.util.impl.CSVUserDataReader;
import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class Storage {

    private final List<Event> events;

    private final List<User> users;

    private final HashMap<Event, List<Ticket>> tickets;

    @Value("${users.file.path}")
    private String userFilePath;

    @Value("${events.file.path}")
    private String eventsFilePath;

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
        users.addAll(csvUserDataReader.getDataFromCSV(userFilePath));
        events.addAll(csvEventDataReader.getDataFromCSV(eventsFilePath));
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
