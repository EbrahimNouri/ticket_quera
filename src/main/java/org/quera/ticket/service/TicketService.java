package org.quera.ticket.service;

import org.quera.ticket.models.BuyTicketDto;
import org.quera.ticket.models.Ticket;
import org.quera.ticket.models.User;

public interface TicketService {
    Ticket[] getTickets(Long id);

    void buyTicket(User user, BuyTicketDto dto);

    boolean reserved(Long seatNumber, Long matchId, Long seatId);
}
