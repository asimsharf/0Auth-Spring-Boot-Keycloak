package com.sudagoarth._Auth.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth._Auth.shared.ApiResponse;

@RestController
@RequestMapping("/api")
public class TicketController {


    @GetMapping("/userInfo")
    public ResponseEntity<ApiResponse> getUserInfo(@AuthenticationPrincipal User principal) {
        if (principal != null) {
            return ResponseEntity.ok(ApiResponse.success("User Info fetched successfully.", principal));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.error("User Info fetch failed.", "UNAUTHORIZED", "User not authenticated"));
        }
    }
    

    @GetMapping("/tickets")
    public ResponseEntity<ApiResponse> getTickets() {

        Set<String> tickets = Set.of("Ticket 1", "Ticket 2", "Ticket 3");

        // Return a success response
        return ResponseEntity.ok(ApiResponse.success("Successfully fetched tickets.", tickets));
    }

    @PostMapping("/tickets")
    public ResponseEntity<ApiResponse> postTickets() {

        boolean isCreated = false; 

        if (isCreated) {
            return ResponseEntity.ok(ApiResponse.success("Tickets created successfully.", null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Tickets creation failed.", "BAD_REQUEST", "Invalid ticket data"));
        }
    }

}
