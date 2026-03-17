package com.example.eventtix;

import com.example.eventtix.repository.EventRepository;
import com.example.eventtix.repository.SeatRepository;
import com.example.eventtix.repository.SectorRepository;
import com.example.eventtix.repository.TicketRepository;
import com.example.eventtix.repository.VenueRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

  @Autowired
  protected EventRepository eventRepository;

  @Autowired
  protected SeatRepository seatRepository;

  @Autowired
  protected VenueRepository venueRepository;

  @Autowired
  protected TicketRepository ticketRepository;

  @Autowired
  protected SectorRepository sectorRepository;

  private static final String IMAGE_NAME = "postgres:15-alpine";

  @LocalServerPort
  private Integer port;

  @Container
  @ServiceConnection
  static PostgreSQLContainer postgres = new PostgreSQLContainer(
      DockerImageName.parse(IMAGE_NAME)
  );

  @AfterEach
  void cleanUp() {
    ticketRepository.deleteAll();
    eventRepository.deleteAll();
    seatRepository.deleteAll();
    sectorRepository.deleteAll();
    venueRepository.deleteAll();
  }
}
