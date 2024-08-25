package com.ticketland.persistence.util.impl;

import com.ticketland.entities.User;
import com.ticketland.persistence.util.CSVDataReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CSVUserDataReader implements CSVDataReader<User> {

    private final List<User> users;

    @Value("classpath:users-data.csv")
    private File userResource;


    public CSVUserDataReader() {
        users = new ArrayList<>();
    }

    public List<User> getDataFromCSV() {
        try {

            Scanner myReader = new Scanner(userResource);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] raw = data.split(",");
                users.add(new User(raw[0], raw[1], raw[2]));
            }

            myReader.close();

            return users;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
