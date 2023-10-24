package org.quera.ticket.controller;

import org.quera.ticket.models.SeatClass;
import org.quera.ticket.service.SeatClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat_classes")
public class SeatClassController {

    @Autowired
    private SeatClassService seatClassService;

    @GetMapping
    SeatClass[] getSeatClasses() {
        return seatClassService.getAllSeatClasses();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity addSeatClass(@RequestBody SeatClass seatClass) {
        try {
            seatClassService.addSeatClass(seatClass);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
