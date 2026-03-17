package com.example.eventtix.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CreateEventRequest(
    String name,
    Instant startsAt,
    UUID venueId,
    BigDecimal price
) {}
