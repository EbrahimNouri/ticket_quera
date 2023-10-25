package org.quera.ticket.repository;

import org.quera.ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findTicketsByUserId(Long userId);

    @Query("from Ticket t where t.seatNumber = :seatNumber and t.seatClass.match.id = :matchId and t.seatClass.id = :seatId")
    boolean existsTicket(Long seatNumber, Long matchId, Long seatId);
}
