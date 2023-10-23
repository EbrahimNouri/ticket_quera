package org.quera.ticket.service;

import org.quera.ticket.models.Team;
import org.quera.ticket.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public Team[] getTeams() {
        return teamRepository.findAll().toArray(new Team[0]);
    }

}
