package com.ticketland.config;

import com.ticketland.daos.UserDAO;
import com.ticketland.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("hello")
    private String userName;

    @Autowired
    private UserDAO userDAO;

    @Bean
    public UserService userService(){
        return new UserService(userDAO);
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAO();
    }
}
