package edu.tennis_scoreboard.model.ongoing_match.match_stage;

public abstract class DisputeStage<T> extends MatchStage<T> {

    public DisputeStage(Long playerOneId, Long playerTwoId) {
        super(playerOneId, playerTwoId);
    }
}
