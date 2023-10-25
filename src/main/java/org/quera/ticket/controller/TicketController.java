package org.quera.ticket.controller;

import org.quera.ticket.models.Ticket;
import org.quera.ticket.models.User;
import org.quera.ticket.service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @GetMapping
    Ticket[] getTickets(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ticketServiceImpl.getTickets(user.getId());
    }
}
