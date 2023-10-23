package org.quera.ticket.service;

import org.quera.ticket.models.Stadium;
import org.quera.ticket.models.User;
import org.quera.ticket.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class StadiumServiceImpl implements StadiumService{

    @Autowired
    private StadiumRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Stadium[] getStadiums() {
        return repository.findAll().toArray(new Stadium[0]);
    }

    @Override
    public void create(Stadium stadium, String username) {
        userService.existByUsername(username);
        repository.save(stadium);
    }

    @Override
    public Stadium getStadium(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("Error: stadium not found"));
    }

    @Override
    public void delete(Long id) {
        repository.delete(getStadium(id));

    }
}
