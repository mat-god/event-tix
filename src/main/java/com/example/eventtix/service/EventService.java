package com.example.eventtix.service;

import com.example.eventtix.dto.CreateEventRequest;
import com.example.eventtix.model.Event;
import com.example.eventtix.model.Venue;
import com.example.eventtix.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;

  private final VenueService venueService;

  private final TicketService ticketService;

  @Transactional
  public Event createEvent(CreateEventRequest createEventRequest) {
    Venue venue = venueService.getVenueById(createEventRequest.venueId());

    Event event = eventRepository.save(Event.builder()
        .venue(venue)
        .name(createEventRequest.name())
        .startsAt(createEventRequest.startsAt())
        .build());

    ticketService.createTicketsForEvent(event, createEventRequest.price());

    return event;
  }
}
