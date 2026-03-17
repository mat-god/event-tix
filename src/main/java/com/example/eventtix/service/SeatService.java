package com.example.eventtix.service;

import com.example.eventtix.model.Seat;
import com.example.eventtix.repository.SeatRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatService {

  private final SeatRepository seatRepository;

  public List<Seat> findByVenueId(UUID venueId) {
    return seatRepository.findAllBySector_Venue_Id(venueId);
  }
}
