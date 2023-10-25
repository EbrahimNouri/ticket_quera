package org.quera.ticket.service;

import org.quera.ticket.models.Ticket;
import org.quera.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public Ticket[] getTickets(Long id) {
        return ticketRepository.findTicketsByUserId(id).toArray(new Ticket[0]);
    }
}
