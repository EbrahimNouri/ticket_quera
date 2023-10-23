package org.quera.ticket.service;

import org.quera.ticket.customException.ExistException;
import org.quera.ticket.customException.NotFoundException;
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

    @Override
    public Team getTeam(Long id) {
       return teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Error: team not found"));
    }

    @Override
    public void createTeam(Team team) {
        if (teamRepository.existsByName(team.getName()))
            throw new ExistException("Error: team not found");
        teamRepository.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        if (!teamRepository.existsById(id))
            throw new ExistException("Error: team not found");
        teamRepository.deleteById(id);
    }

    @Override
    public boolean existsTeam(Long id) {
        return teamRepository.existsById(id);
    }

}
