package org.quera.ticket.service;

import org.quera.ticket.models.Ticket;

public interface TicketService {
    Ticket[] getTickets(Long id);
}
