package org.quera.ticket.controller;

import org.quera.ticket.customException.TicketException;
import org.quera.ticket.models.BuyTicketDto;
import org.quera.ticket.models.Ticket;
import org.quera.ticket.models.User;
import org.quera.ticket.service.TicketService;
import org.quera.ticket.service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    Ticket[] getTickets(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ticketService.getTickets(user.getId());
    }

    @PostMapping
    ResponseEntity buyTicket(Authentication authentication, BuyTicketDto dto){
        User user = (User) authentication.getPrincipal();
       try {
           ticketService.buyTicket(user, dto);
           return ResponseEntity.ok().build();
       }catch (TicketException e){
           return ResponseEntity.status(400).build();
       }
    }
}
