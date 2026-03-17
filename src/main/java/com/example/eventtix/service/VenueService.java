package com.example.eventtix.service;

import com.example.eventtix.exceptions.VenueNotFoundException;
import com.example.eventtix.model.Venue;
import com.example.eventtix.repository.VenueRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {

  private final VenueRepository venueRepository;

  public Venue getVenueById(UUID venueId) {
    return venueRepository.findById(venueId)
        .orElseThrow(() -> new VenueNotFoundException(venueId));
  }
}
