package org.quera.ticket.controller;

import org.quera.ticket.models.Stadium;
import org.quera.ticket.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @GetMapping
    Stadium[] getStadiums() {
        return stadiumService.getStadiums();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    ResponseEntity<String> create(@RequestBody Stadium stadium, @AuthenticationPrincipal UserDetails details) {
        if (stadium.getCapacity() < 0)
            return ResponseEntity.status(400).body("Error: invalid balance");

        stadiumService.create(stadium, details.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    ResponseEntity getStadium(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(stadiumService.getStadium(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteStadium(@PathVariable Long id) {

        try {
            stadiumService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
