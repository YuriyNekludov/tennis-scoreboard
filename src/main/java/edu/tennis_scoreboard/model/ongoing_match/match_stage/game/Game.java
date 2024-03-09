package edu.tennis_scoreboard.model.ongoing_match.match_stage.game;

import edu.tennis_scoreboard.model.ongoing_match.State;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.DisputeStage;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.MatchStage;
import lombok.Getter;


import java.util.HashMap;

public class Game extends MatchStage<Point> {

    private boolean isDuese;
    @Getter
    protected DisputeStage<Point> disputeStage;

    public Game(Long playerOneId, Long playerTwoId) {
        super(playerOneId, playerTwoId);
        playersScore = new HashMap<>();
        playersScore.put(playerOne, Point.LOVE);
        playersScore.put(playerTwo, Point.LOVE);
        isDuese = false;
    }

    @Override
    public void calculateScore(Long id) {
        if (isDeuseGame()) {
            disputeStage.calculateScore(id);
            if (disputeStage.getState() != State.ON_GOING)
                state = disputeStage.getState();
        } else {
            if (playersScore.get(id) == Point.FORTY)
                state = getWinnerById(id);
            else
                addPoints(id);
        }
    }

    @Override
    public String printScore(Long id) {
        if (disputeStage != null) {
            if (playersScore.get(id) == Point.ADVANTAGE)
                return "Больше";
            else if (playersScore.get(getOpponentId(id)) == Point.ADVANTAGE)
                return "Меньше";
            return "Ровно";
        }
        return playersScore.get(id).value;
    }

    @Override
    protected void addPoints(Long id) {
        switch (playersScore.get(id)) {
            case LOVE -> playersScore.put(id, Point.FIFTEEN);
            case FIFTEEN -> playersScore.put(id, Point.THIRTY);
            case THIRTY -> playersScore.put(id, Point.FORTY);
        }
    }

    private boolean isDeuseGame() {
        if (isDuese)
            return true;
        else if (playersScore.get(playerOne) == Point.FORTY
                && playersScore.get(playerTwo) == Point.FORTY) {
            isDuese = true;
            disputeStage = new DeuseGame(playerOne, playerTwo, playersScore);
            return true;
        } else
            return false;
    }
}