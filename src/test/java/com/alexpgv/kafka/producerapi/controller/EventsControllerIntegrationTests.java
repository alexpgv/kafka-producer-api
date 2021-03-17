package com.alexpgv.kafka.producerapi.controller;

import com.alexpgv.kafka.producerapi.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(topics = {"events"}, partitions = 1)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
public class EventsControllerIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void postEvent() {
        // given
        Event event = Event.builder()
                .EventId(1)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<Event> request = new HttpEntity<>(event, headers);

        // when
        ResponseEntity<Event> responseEntity = restTemplate.exchange("/v1/event", HttpMethod.POST, request, Event.class);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
