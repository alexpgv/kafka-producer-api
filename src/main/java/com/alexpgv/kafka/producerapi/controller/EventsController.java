package com.alexpgv.kafka.producerapi.controller;

import com.alexpgv.kafka.producerapi.domain.Event;
import com.alexpgv.kafka.producerapi.producer.EventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventsController {

    @Autowired
    EventProducer eventProducer;

    @PostMapping("/v1/event")
    public ResponseEntity<Event> postEvent(@RequestBody Event event) throws JsonProcessingException {
        eventProducer.sendEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

}
