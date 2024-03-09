package edu.tennis_scoreboard.servicie;

import edu.tennis_scoreboard.model.ongoing_match.OngoingMatch;

public class MatchScoreCalculationService {

    public synchronized void calculate(OngoingMatch match, Long id) {
        match.getMatchScore().calculateMatchScore(match, id);
    }
}
