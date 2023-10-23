package org.quera.ticket.service;

import org.quera.ticket.customException.AddMatchException;
import org.quera.ticket.customException.NotFoundException;
import org.quera.ticket.models.Match;
import org.quera.ticket.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private StadiumService stadiumService;

    @Override
    public Match[] getMatches() {
        return matchRepository.findAll().toArray(new Match[0]);

    }

    @Override
    public void addMatch(Match match) {
        if (match.getDate().before(new Date()))
            throw new AddMatchException("Error: the given date has been passed");

        if (!teamService.existsTeam(match.getHome().getId()))
            throw new AddMatchException("Error: invalid home team id");

        if (!teamService.existsTeam(match.getAway().getId()))
            throw new AddMatchException("Error: invalid away team id");

        if (!stadiumService.existsById(match.getStadium().getId()))
            throw new AddMatchException("Error: invalid stadium id");

        if (matchRepository.existsMathByDate(match.getDate()))
            throw new AddMatchException("Error: stadium is full in the given date");

        matchRepository.save(match);
    }

    @Override
    public Match getMatch(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new NotFoundException("Error: match not found"));
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.delete(getMatch(id));
    }
}
