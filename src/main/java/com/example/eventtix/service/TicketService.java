package com.example.eventtix.service;

import com.example.eventtix.model.Event;
import com.example.eventtix.model.Seat;
import com.example.eventtix.model.Ticket;
import com.example.eventtix.model.TicketStatus;
import com.example.eventtix.repository.TicketRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

  private final TicketRepository ticketRepository;
  private final SeatService seatService;

  @Transactional
  public void createTicketsForEvent(Event event, BigDecimal price) {
    ticketRepository.saveAll(
        seatService.findByVenueId(event.getVenue().getId()).stream()
            .map(seat -> this.buildTicket(event, seat, price))
            .toList()
    );
  }

  private Ticket buildTicket(Event event, Seat seat, BigDecimal price) {
    return Ticket.builder()
        .event(event)
        .seat(seat)
        .price(price)
        .status(TicketStatus.AVAILABLE)
        .build();
  }
}
