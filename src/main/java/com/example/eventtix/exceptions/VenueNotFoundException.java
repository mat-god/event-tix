package com.example.eventtix.exceptions;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VenueNotFoundException extends RuntimeException {

  public VenueNotFoundException(UUID venueId) {
    super("Venue with id " + venueId + " not found");
  }
}
