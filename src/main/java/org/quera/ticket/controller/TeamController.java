package org.quera.ticket.controller;

import org.quera.ticket.models.Team;
import org.quera.ticket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/teams")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    Team[] getTeams() {
        return teamService.getTeams();
    }
}

