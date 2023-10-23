package org.quera.ticket.controller;

import org.quera.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/update_balance")
    public ResponseEntity<String> updateBalance(@PathVariable Long id, @RequestBody BigDecimal balance) {
        userService.updateBalance(id, balance);
        return ResponseEntity.ok("Balance updated successfully.");
    }
}
