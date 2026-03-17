package com.example.eventtix.repository;

import com.example.eventtix.model.Seat;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

  List<Seat> findAllBySector_Venue_Id(UUID venueId);
}
