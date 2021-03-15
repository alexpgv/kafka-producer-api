package com.alexpgv.kafka.producerapi.controller;

import com.alexpgv.kafka.producerapi.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    private static final Logger logger = LoggerFactory.getLogger(EventsController.class);

    @PostMapping("/v1/event")
    public ResponseEntity<Event> postEvent(@RequestBody Event event) {
        logger.info("Event created");
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

}
