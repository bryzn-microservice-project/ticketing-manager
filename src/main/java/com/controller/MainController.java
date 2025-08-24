package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * MainController.java reponsible for handling incoming requests and delegating other classes to
 * handle the topics
 */
@RestController
public class MainController {
    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
    private static int ticket = 1;

    @GetMapping("/api/v1/name")
    public String microserviceName() {
        return "This microservice is the [TICKETING-MANAGER]!";
    }

    /*
     * Main entry point for processing incoming topics other microservices will use this enpoint
     */
    @PostMapping("/api/v1/ticket")
    public ResponseEntity<String> ticketRequest() {
        LOG.info("RECEIVED A REQUEST FOR A TICKET ID");
        return ResponseEntity.ok(generateTicket());
    }

    private String generateTicket()
    {
        String prefix = "806";
        // recycle ticket IDs after 9999
        int ticketID = ticket % 9999;
        ticket++;
        LOG.info("GENERATED TICKET ID: {}", String.format("%s%04d", prefix, ticketID));
        return String.format("%s%04d", prefix, ticketID);
    }
}
