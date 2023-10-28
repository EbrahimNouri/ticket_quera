package org.quera.ticket.repository;

import org.quera.ticket.models.SeatClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatClassRepository extends JpaRepository<SeatClass, Long> {

    boolean existsByMatch_Id(Long matchId);
}
