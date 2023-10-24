package org.quera.ticket.controller;

import org.quera.ticket.customException.NotFoundException;
import org.quera.ticket.customException.SeatClassException;
import org.quera.ticket.models.SeatClass;
import org.quera.ticket.service.SeatClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    @GetMapping("/{id}")
    ResponseEntity getSeatClass(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(seatClassService.getSeatClass(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}