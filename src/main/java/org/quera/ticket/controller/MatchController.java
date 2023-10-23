package org.quera.ticket.controller;

import org.quera.ticket.customException.AddMatchException;
import org.quera.ticket.models.Match;
import org.quera.ticket.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping
    Match[] getMatches(){
        return matchService.getMatches();
    }

    @PostMapping
    ResponseEntity createMatch(@RequestBody Match match){
        try {
            matchService.addMatch(match);
            return ResponseEntity.ok().build();
        }catch (AddMatchException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
