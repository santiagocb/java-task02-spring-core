package com.ticketland.services;

import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;
import com.ticketland.programs.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private UserDAO userDAO;

    public void registerUser(User user) {
        userDAO.createUser(user);
    }

    public User getUserById(String userId) {
        return userDAO.getUserById(userId).orElseThrow();
    }

    public void showUsers() {
        logger.info("All registered users:");
        userDAO.getUsers().forEach(u -> logger.info(u.toString()));
    }

}
