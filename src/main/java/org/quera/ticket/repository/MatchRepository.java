package org.quera.ticket.repository;

import org.quera.ticket.models.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("select case when count(m)> 0 then true else false end from Match m where m.date = :date")
    Boolean existsMathByDate(Date date);

}
