package com.ticketland.daos;

import com.ticketland.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {

    @Autowired
    private List<User> users;

    public UserDAO() {
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
