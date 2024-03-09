package edu.tennis_scoreboard.model.ongoing_match;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class OngoingMatch {

    private final UUID id;
    private final Long playerOneId;
    private final Long playerTwoId;
    private final String playerOneName;
    private final String playerTwoName;
    private final MatchScore matchScore;
    private Long winnerId;
    @Setter
    private State state;

    public OngoingMatch(
            Long playerOneId,
            Long playerTwoId,
            String playerOneName,
            String playerTwoName) {
        this.id = UUID.randomUUID();
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        matchScore = new MatchScore(playerOneId, playerTwoId);
        state = State.ON_GOING;
    }

    public void setWinnerId() {
        switch (state) {
            case PLAYER_ONE_WIN -> winnerId = playerOneId;
            case PLAYER_TWO_WIN -> winnerId = playerTwoId;
        }
    }
}
