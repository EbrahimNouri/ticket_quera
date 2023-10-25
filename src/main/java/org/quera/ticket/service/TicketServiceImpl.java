package org.quera.ticket.service;

import org.quera.ticket.customException.NotFoundException;
import org.quera.ticket.customException.TicketException;
import org.quera.ticket.models.*;
import org.quera.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository TICKET_REPOSITORY;
    private final MatchService MATCH_SERVICE;
    private final SeatClassService SEATCLASS_SERVICE;

    public TicketServiceImpl(TicketRepository TICKET_REPOSITORY, MatchService MATCH_SERVICE, SeatClassService SEATCLASS_SERVICE) {
        this.TICKET_REPOSITORY = TICKET_REPOSITORY;
        this.MATCH_SERVICE = MATCH_SERVICE;
        this.SEATCLASS_SERVICE = SEATCLASS_SERVICE;
    }

    @Override
    public Ticket[] getTickets(Long id) {
        return TICKET_REPOSITORY.findTicketsByUserId(id).toArray(new Ticket[0]);
    }

    @Override
    public void buyTicket(User user, BuyTicketDto dto) {

        Match match;
        SeatClass seatClass;

        try {
            match = MATCH_SERVICE.getMatch(dto.getMatch_id());

        } catch (NotFoundException ig) {
            throw new TicketException("Error: invalid match id");
        }

        try {
            seatClass = SEATCLASS_SERVICE.getSeatClass(dto.getSeat_number());
        } catch (NotFoundException ig) {
            throw new TicketException("Error: the seat is not available");
        }

        if (match.getDate().before(new Date()))
            throw new TicketException("Error: the match has been finished");

        
        if (dto.getSeat_number() < 1 || dto.getSeat_number() > match.getStadium().getCapacity())
            throw new TicketException("Error: invalid seat number");

        boolean purchasedBefore = Stream.of(getTickets(user.getId())).anyMatch(ticket ->
                ticket.getSeatClass().getMatch().getId().equals(dto.getMatch_id())
                        && ticket.getSeatClass().equals(seatClass));

        if (purchasedBefore)
            throw new TicketException("Error: you have already reserved this seat");

        if (reserved(dto.getSeat_number(), dto.getMatch_id(), seatClass.getId()))
            throw new TicketException("Error: the seat is reserved by another user");

        if (user.getBalance().compareTo(seatClass.getPrice()) < 0)
            throw new TicketException("Error: not enough balance");
        
        Ticket ticket = new Ticket(null, user, seatClass, dto.getSeat_number());

        TICKET_REPOSITORY.save(ticket);
    }

    private boolean reserved(Long seatNumber, Long matchId, Long seatId) {
        return TICKET_REPOSITORY.existsTicket(seatNumber, matchId, seatId);
    }
}
