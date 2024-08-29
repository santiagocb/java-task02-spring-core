package com.ticketland.config.persistence.util;

import com.ticketland.entities.User;
import com.ticketland.persistence.util.impl.CSVUserDataReader;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVUserDataReaderTest {

    @Test
    void readCsvFileAndPopulateAList() {

        // It is easier with SpringBoot !

        /* Given ---- */

        // Create an application context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // Register the PropertySourcesPlaceholderConfigurer and component class
        context.register(CSVUserDataReader.class);

        PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();

        // Configure the environment with test properties
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.getPropertySources().addFirst(
                new MapPropertySource("testProperties",
                        java.util.Collections.singletonMap("file.path", "classpath:users.csv"))
        );

        // Add the PropertySourcesPlaceholderConfigurer to the context
        context.addBeanFactoryPostProcessor(propertyConfigurer);
        context.refresh();

        // Get the bean from the context
        CSVUserDataReader csvUserDataReader = context.getBean(CSVUserDataReader.class);

        var expected = new User("TestId1", "TestName1", "TestEmail1");

        // When
        var userListFromFile = csvUserDataReader.getDataFromCSV("users.csv");

        // Then
        assertEquals(expected, userListFromFile.get(0));
        assertEquals(2, userListFromFile.size());

        // Close the context
        context.close();
    }
}
