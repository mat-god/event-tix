package com.example.eventtix.service;

import com.example.eventtix.exceptions.VenueNotFoundException;
import com.example.eventtix.model.Venue;
import com.example.eventtix.repository.VenueRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VenueServiceUnitTest {

  @Mock
  VenueRepository venueRepository;

  @InjectMocks
  VenueService venueService;

  @Test
  void shouldReturnVenueByGivenId() {
    // given
    UUID venueId = UUID.randomUUID();

    Venue venue = Venue.builder().id(venueId).name("Test Venue").build();

    when(venueRepository.findById(venueId)).thenReturn(Optional.of(venue));

    // when
    Venue result = venueService.getVenueById(venueId);

    // then
    assertThat(result).isEqualTo(venue);
  }

  @Test
  void shouldThrowVenueNotFoundException() {
    // given
    UUID venueId = UUID.randomUUID();

    when(venueRepository.findById(venueId)).thenReturn(Optional.empty());

    // when & then
    assertThatThrownBy(() -> venueService.getVenueById(venueId))
        .isInstanceOf(VenueNotFoundException.class);
  }
}