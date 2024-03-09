package edu.tennis_scoreboard.model.ongoing_match.match_stage.set;

import edu.tennis_scoreboard.model.ongoing_match.match_stage.DisputeStage;

import java.util.HashMap;

public class TieBreak extends DisputeStage<Integer> {

    public TieBreak(Long playerOneId, Long playerTwoId) {
        super(playerOneId, playerTwoId);
        playersScore = new HashMap<>();
        playersScore.put(playerOne, 0);
        playersScore.put(playerTwo, 0);
    }

    @Override
    public void calculateScore(Long id) {
        if (tieBreakWinCondition(id)) {
            addPoints(id);
            state = getWinnerById(id);
        } else
            addPoints(id);
    }

    private boolean tieBreakWinCondition(Long id) {
        Long opponentId = getOpponentId(id);
        return playersScore.get(id) >= 6 && (playersScore.get(id) - playersScore.get(opponentId) >= 1);
    }

    @Override
    public String printScore(Long id) {
        return null;
    }

    @Override
    protected void addPoints(Long id) {
        int currentScore = playersScore.get(id);
        playersScore.put(id, currentScore + 1);
    }
}
