package com.example.eventtix.service;

import com.example.eventtix.model.Seat;
import com.example.eventtix.repository.SeatRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeatServiceUnitTest {

  @Mock
  SeatRepository seatRepository;

  @InjectMocks
  SeatService seatService;

  @Test
  void shouldReturnSeatsByVenueId() {
    // given
    UUID venueId = UUID.randomUUID();
    List<Seat> seats = List.of(
        new Seat()
    );

    when(seatRepository.findAllBySector_Venue_Id(venueId)).thenReturn(seats);

    // when
    List<Seat> result = seatService.findByVenueId(venueId);

    // then
    assertThat(result).isEqualTo(seats);
  }

  @Test
  void shouldReturnEmptyListWhenNoSeatsFound() {
    // given
    UUID venueId = UUID.randomUUID();
    when(seatRepository.findAllBySector_Venue_Id(venueId)).thenReturn(List.of());

    // when
    List<Seat> result = seatService.findByVenueId(venueId);

    // then
    assertThat(result).isEmpty();
  }
}