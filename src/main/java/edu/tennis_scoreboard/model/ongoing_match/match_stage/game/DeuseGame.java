package edu.tennis_scoreboard.model.ongoing_match.match_stage.game;

import edu.tennis_scoreboard.model.ongoing_match.match_stage.DisputeStage;

import java.util.Map;

public class DeuseGame extends DisputeStage<Point> {

    public DeuseGame(Long playerOneId, Long playerTwoId, Map<Long, Point> playersScore) {
        super(playerOneId, playerTwoId);
        this.playersScore = playersScore;
    }

    @Override
    public void calculateScore(Long id) {
        if (playersScore.get(id) != Point.ADVANTAGE) {
            addPoints(id);
            refreshDeuseScore();
        } else
            state = getWinnerById(id);
    }

    @Override
    protected void addPoints(Long id) {
        playersScore.put(id, Point.ADVANTAGE);
    }

    private void refreshDeuseScore() {
        if (playersScore.get(playerOne) == Point.ADVANTAGE
                && playersScore.get(playerTwo) == Point.ADVANTAGE) {
            playersScore.put(playerOne, Point.FORTY);
            playersScore.put(playerTwo, Point.FORTY);
        }
    }
}
