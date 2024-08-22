package com.ticketland.services;

import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService() {
    }

    public void registerUser(User user) {
        userDAO.createUser(user);
    }

    public Optional<User> getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    public void showUsers() {
        userDAO.getUsers().forEach(System.out::println);
    }

}
