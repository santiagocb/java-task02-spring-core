package com.ticketland.svc;

import com.ticketland.daos.EventDAO;
import com.ticketland.entities.Event;
import com.ticketland.entities.Location;
import com.ticketland.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventDAO eventDAO;

    @Autowired
    @InjectMocks
    EventService eventService;


    @Test
    public void registerAnUser() {
        // Given
        var event = new Event("eventId1", "eventName1", "placeTest1", new Location(0, 0), LocalDate.now());

        // Test with explicit openMocks operation to inject mocks to Autowired service during the test execution
        MockitoAnnotations.openMocks(this);

        when(eventDAO.getEventById(anyString())).thenReturn(Optional.of(event));

        // When
        var result = eventService.getEventById("anyString");

        // Then
        assertEquals(result, event);
    }
}
