package com.example.eventtix.repository;

import com.example.eventtix.model.Venue;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, UUID> {

}
