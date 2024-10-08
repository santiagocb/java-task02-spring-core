package com.ticketland.services;

import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;
import com.ticketland.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    UserService userService;


    @Test
    public void getAnUserById() {
        // Given
        var user = new User("idTest1", "UserTest1", "email@test1.com");

        when(userDAO.getUserById(anyString())).thenReturn(Optional.of(user));

        // When
        var result = userService.getUserById("idTest1");

        // Then
        assertEquals(result, user);
    }

    @Test
    public void getAnUserByIdAndThrowAGeneralExceptionWhenUserNotFound() {
        // Given
        var user = new User("idTest1", "UserTest1", "email@test1.com");

        when(userDAO.getUserById(anyString())).thenReturn(Optional.empty());

        // When - Then
        assertThrows(NoSuchElementException.class, () -> userService.getUserById("idTest1"));
    }
}
