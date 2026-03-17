package com.example.eventtix.controller;

import com.example.eventtix.common.ApiUrls;
import com.example.eventtix.dto.CreateEventRequest;
import com.example.eventtix.model.Event;
import com.example.eventtix.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrls.EVENTS_URL)
public class EventController {

  private final EventService eventService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Event createEvent(@RequestBody CreateEventRequest createEventRequest) {
    return eventService.createEvent(createEventRequest);
  }
}
