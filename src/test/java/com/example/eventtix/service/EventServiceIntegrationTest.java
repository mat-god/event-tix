package com.example.eventtix.service;

import com.example.eventtix.AbstractIntegrationTest;
import com.example.eventtix.model.Event;
import com.example.eventtix.model.Seat;
import com.example.eventtix.model.Sector;
import com.example.eventtix.model.Ticket;
import com.example.eventtix.model.TicketStatus;
import com.example.eventtix.model.Venue;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private EventService eventService;

  @Test
  void shouldCreateEventAndGenerateTickets() {
    // given
    Venue venue = this.createVenueWithSectorsAndSeats();

    UUID venueId = venue.getId();
    String eventName = "Test Event";
    Instant startsAt = Instant.now().plusSeconds(86400);
    BigDecimal price = BigDecimal.valueOf(299.0);

    // when
    eventService.createEvent(venueId, eventName, startsAt, price);

    // then
    List<Event> events = eventRepository.findAll();
    assertThat(events).hasSize(1);
    Event event = events.getFirst();

    List<Ticket> tickets = ticketRepository.findAll();

    assertThat(tickets)
        .hasSize(4)
        .allSatisfy(ticket -> {
      assertThat(ticket.getPrice()).isEqualByComparingTo(price);
      assertThat(ticket.getStatus()).isEqualTo(TicketStatus.AVAILABLE);
      assertThat(ticket.getEvent().getId()).isEqualTo(event.getId());
    });
  }

  private Venue createVenueWithSectorsAndSeats() {
    Venue venue = venueRepository.save(Venue.builder().name("Test Venue").build());

    Sector sectorA = sectorRepository.save(Sector.builder().name("A").venue(venue).build());
    Sector sectorB = sectorRepository.save(Sector.builder().name("B").venue(venue).build());

    seatRepository.saveAll(List.of(
        Seat.builder().number(1).rowNumber(1).sector(sectorA).build(),
        Seat.builder().number(1).rowNumber(1).sector(sectorB).build(),
        Seat.builder().number(2).rowNumber(1).sector(sectorA).build(),
        Seat.builder().number(2).rowNumber(1).sector(sectorB).build()
    ));

    return venue;
  }
}