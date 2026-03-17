package com.example.eventtix;

import com.example.eventtix.repository.EventRepository;
import com.example.eventtix.repository.SeatRepository;
import com.example.eventtix.repository.SectorRepository;
import com.example.eventtix.repository.TicketRepository;
import com.example.eventtix.repository.VenueRepository;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EventTixApplication.class)
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

  @Autowired
  protected MockMvc mvc;

  private static final String IMAGE_NAME = "postgres:15-alpine";

  @LocalServerPort
  private Integer port;

  @Container
  @ServiceConnection
  static PostgreSQLContainer postgres = new PostgreSQLContainer(
      DockerImageName.parse(IMAGE_NAME)
  );

  @BeforeEach
  void init() {
    RestAssuredMockMvc.mockMvc(mvc);
    RestAssured.port = port;
  }

  @AfterEach
  void cleanUp() {
    ticketRepository.deleteAll();
    eventRepository.deleteAll();
    seatRepository.deleteAll();
    sectorRepository.deleteAll();
    venueRepository.deleteAll();
  }
}
