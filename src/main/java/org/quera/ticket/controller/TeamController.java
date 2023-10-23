package org.quera.ticket.controller;

import org.quera.ticket.customException.ExistException;
import org.quera.ticket.customException.NotFoundException;
import org.quera.ticket.models.Team;
import org.quera.ticket.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/teams")
@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    Team[] getTeams() {
        return teamService.getTeams();
    }

    @PostMapping()
    ResponseEntity createTeam(@RequestBody Team team) {
        try {
            teamService.createTeam(team);
            return ResponseEntity.ok().build();
        } catch (ExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.ok().build();
        } catch (ExistException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    ResponseEntity getTeam(@PathVariable Long id){
        try {
            teamService.getTeam(id);
            return ResponseEntity.ok().build();
        }catch (NotFoundException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}

