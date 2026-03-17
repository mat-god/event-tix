package com.example.eventtix.service;

import com.example.eventtix.model.Event;
import com.example.eventtix.model.Venue;
import com.example.eventtix.repository.EventRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;

  private final VenueService venueService;

  private final TicketService ticketService;

  @Transactional
  public void createEvent(UUID venueId, String eventName, Instant startsAt, BigDecimal price) {
    Venue venue = venueService.getVenueById(venueId);

    Event event = eventRepository.save(Event.builder()
        .venue(venue)
        .name(eventName)
        .startsAt(startsAt)
        .build());

    ticketService.createTicketsForEvent(event, price);
  }
}
