package com.ticketland.daos;

import com.ticketland.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private final List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
    }

    public void createUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> getUserById(String id) {
        return users.stream().filter(u -> u.id().equals(id)).findFirst();
    }
}
