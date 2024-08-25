package com.ticketland.persistence.util.impl;

import com.ticketland.entities.Event;
import com.ticketland.entities.Location;
import com.ticketland.persistence.util.CSVDataReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CSVEventDataReader implements CSVDataReader<Event> {

    private final List<Event> events;

    @Value("classpath:events-data.csv")
    private File eventResource;

    public CSVEventDataReader() {
        events = new ArrayList<>();
    }

    @Override
    public List<Event> getDataFromCSV() {
        try {
            Scanner myReader = new Scanner(eventResource);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] raw = data.split(",");
                events.add(new Event(raw[0], raw[1], raw[2],new Location(Double.parseDouble(raw[3]), Double.parseDouble(raw[4])), LocalDate.parse(raw[5])));
            }

            myReader.close();

            return events;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
