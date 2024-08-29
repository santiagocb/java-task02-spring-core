package com.ticketland.config;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppConfig {

    @Test
    public void userCsvFileEnvironmentValueValidation() throws Exception {
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new ResourcePropertySource("application.properties"));

        String filePath = env.getProperty("users.file.path");
        assertEquals("users.csv", filePath);
    }
}
