package edu.tennis_scoreboard.model.ongoing_match.match_stage.set;

import edu.tennis_scoreboard.model.ongoing_match.State;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.DisputeStage;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.MatchStage;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.game.Game;
import lombok.Getter;

import java.util.HashMap;

public class Set extends MatchStage<Integer> {

    @Getter
    private Game gameStage;
    private boolean isTieBreak;
    @Getter
    protected DisputeStage<Integer> disputeStage;

    public Set(Long playerOneId, Long playerTwoId) {
        super(playerOneId, playerTwoId);
        playersScore = new HashMap<>();
        playersScore.put(playerOne, 0);
        playersScore.put(playerTwo, 0);
        isTieBreak = false;
        gameStage = new Game(playerOneId, playerTwoId);
    }

    @Override
    public void calculateScore(Long id) {
        if (isTieBreak()) {
            disputeStage.calculateScore(id);
            if (disputeStage.getState() != State.ON_GOING)
                state = disputeStage.getState();
        } else {
            gameStage.calculateScore(id);
            if (gameStage.getState() != State.ON_GOING) {
                if (setWinCondition(id)) {
                    state = getWinnerById(id);
                    addPoints(id);
                } else {
                    addPoints(id);
                    gameStage = new Game(playerOne, playerTwo);
                }
            }
        }
    }

    @Override
    protected void addPoints(Long id) {
        int currentScore = playersScore.get(id);
        playersScore.put(id, currentScore + 1);
    }

    private boolean isTieBreak() {
        if (isTieBreak)
            return true;
        else if (playersScore.get(playerOne) == 6 && playersScore.get(playerTwo) == 6) {
            isTieBreak = true;
            disputeStage = new TieBreak(playerOne, playerTwo);
            return true;
        } else
            return false;
    }

    private boolean setWinCondition(Long id) {
        Long opponentId = getOpponentId(id);
        return playersScore.get(id) >= 5 && (playersScore.get(id) - playersScore.get(opponentId) >= 1);
    }
}
