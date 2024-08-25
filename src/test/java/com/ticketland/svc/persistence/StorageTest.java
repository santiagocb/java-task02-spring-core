package com.ticketland.svc.persistence;

import com.ticketland.entities.User;
import com.ticketland.persistence.Storage;
import com.ticketland.persistence.util.impl.CSVEventDataReader;
import com.ticketland.persistence.util.impl.CSVUserDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class StorageTest {

    @Mock
    private CSVUserDataReader csvUserDataReader;

    @Mock
    private CSVEventDataReader csvEventDataReader;

    @InjectMocks
    Storage storage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void initializeDataStorageAsEmptyLists() {
        // Given - When
        var users = storage.getUsers();
        var events = storage.getEvents();
        var tickets = storage.getTickets();

        // Then
        assertEquals(new ArrayList<>(), users);
        assertEquals(new ArrayList<>(), events);
        assertEquals(new HashMap<>(), tickets);
    }

    @Test
    void initializeDataStorageWhenReadersHaveDataFromFiles() {
        // Given
        var expectedUsers = List.of(
                new User("idTest1", "UserTest1", "email@test1.com"),
                new User("idTest2", "UserTest2", "email@test2.com"),
                new User("idTest3", "UserTest3", "email@test3.com")
        );

        // When
        when(csvUserDataReader.getDataFromCSV()).thenReturn(expectedUsers);

        // Create and refresh the application context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // Register mocks as beans
        context.registerBean(CSVUserDataReader.class, () -> csvUserDataReader);
        context.registerBean(CSVEventDataReader.class, () -> csvEventDataReader);

        context.register(Storage.class);
        context.refresh();

        // Retrieve the Storage bean and check the state
        Storage storageBean = context.getBean(Storage.class);

        // Then
        var users = storageBean.getUsers();

        assertEquals(expectedUsers, users);
    }

    @Test
    void storageConfigShouldCreateStorageBeans() {
        // Given
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(CSVUserDataReader.class);
        context.register(CSVEventDataReader.class);
        context.register(Storage.class);

        context.refresh();

        // When
        Object userStorage = context.getBean("users");

        // Then
        assertNotNull(userStorage);

        context.close();
    }
}
