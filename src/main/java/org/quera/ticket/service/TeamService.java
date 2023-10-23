package org.quera.ticket.service;

import org.quera.ticket.models.Team;

public interface TeamService {
    Team[] getTeams();

    Team getTeam(Long id);

    void createTeam(Team team);

    void deleteTeam(Long id);

    boolean existsTeam(Long id);
}
