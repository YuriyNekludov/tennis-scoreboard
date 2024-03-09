package edu.tennis_scoreboard.model.ongoing_match.match_stage;

import edu.tennis_scoreboard.model.ongoing_match.State;
import lombok.Getter;

import java.util.Map;

public abstract class MatchStage<T> {

    @Getter
    protected Map<Long, T> playersScore;
    protected final Long playerOne;
    protected final Long playerTwo;
    @Getter
    protected State state;

    public MatchStage(Long playerOneId, Long playerTwoId) {
        this.playerOne = playerOneId;
        this.playerTwo = playerTwoId;
        state = State.ON_GOING;
    }

    public abstract void calculateScore(Long id);

    public String printScore(Long id) {
        return playersScore.get(id).toString();
    }

    protected abstract void addPoints(Long id);

    protected State getWinnerById(Long id) {
        return id.equals(playerOne)
                ? State.PLAYER_ONE_WIN
                : State.PLAYER_TWO_WIN;
    }

    protected Long getOpponentId(Long id) {
        return id.equals(playerOne)
                ? playerTwo
                : playerOne;
    }
}
