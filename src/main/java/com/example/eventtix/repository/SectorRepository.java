package com.example.eventtix.repository;

import com.example.eventtix.model.Sector;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, UUID> {

}
