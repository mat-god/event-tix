package com.example.eventtix.repository;

import com.example.eventtix.model.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
