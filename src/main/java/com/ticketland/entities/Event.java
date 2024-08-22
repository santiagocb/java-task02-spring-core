package com.ticketland.entities;

import java.time.LocalDate;

public record Event(String id, String name, String place, Location location, LocalDate date) {}
