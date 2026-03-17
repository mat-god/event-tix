package com.example.eventtix.repository;

import com.example.eventtix.model.Ticket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

}
