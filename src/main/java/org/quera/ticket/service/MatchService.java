package org.quera.ticket.service;

import org.quera.ticket.models.Match;

public interface MatchService {
    Match[] getMatches();

    void addMatch(Match match);

    Match getMatch(Long id);
}
