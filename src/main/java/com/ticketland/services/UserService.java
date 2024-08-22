package com.ticketland.services;

import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;

public class UserService {

    UserDAO userDAO;

    public UserService(UserDAO userDao) {
        this.userDAO = userDao;
    }

    public void registerUser(User user) {
        userDAO.createUser(user);
    }

    public void showUsers() {
        userDAO.getUsers().forEach(System.out::println);
    }

}
