package com.ticketland.unit;

import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    static List<User> users = new ArrayList<>();

    @BeforeAll
    static void initData() {
        users.add(new User("idTest1", "UserTest1", "email@test1.com"));
    }

    @Test
    @DisplayName("UserDAO should get an user by id")
    public void getAnUserByID() {
        // Given
        UserDAO userDAO = new UserDAO(users);

        // When
        var result = userDAO.getUserById("idTest1");

        // Then
        var expected = users.stream().filter(u -> u.id().equals("idTest1")).findFirst();
        assertEquals(result, expected);
    }

    @Test
    public void createAnUser() {
        // Given
        UserDAO userDAO = new UserDAO(users);

        // When
        userDAO.createUser(new User("idTest2", "UserTest2", "email@test2.com"));

        // Then
        var expected = users.stream().filter(u -> u.id().equals("idTest2")).findFirst();
        var result = userDAO.getUserById("idTest2");

        assertEquals(result, expected);
    }

    @Test
    public void getAllUsers() {
        // Given
        UserDAO userDAO = new UserDAO(users);

        // When
        var result = userDAO.getUsers();
        var expected = List.of(new User("idTest1", "UserTest1", "email@test1.com"));

        // Then
        assertIterableEquals(result, expected);
    }
}
