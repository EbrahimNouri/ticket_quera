package org.quera.ticket.service;

import org.quera.ticket.models.Stadium;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface StadiumService {

    public Stadium[] getStadiums();

    void create(Stadium stadium, String details);

    Stadium getStadium(Long id);

    void delete(Long id);

    boolean existsById(Long id);
}
