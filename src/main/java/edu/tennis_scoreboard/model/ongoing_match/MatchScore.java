package edu.tennis_scoreboard.model.ongoing_match;

import edu.tennis_scoreboard.model.ongoing_match.match_stage.set.Set;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MatchScore {

    private Set setStage;
    private final Map<Long, Integer> playersMatchScore;

    public MatchScore(Long playerOneId, Long playerTwoId) {
        playersMatchScore = new HashMap<>();
        playersMatchScore.put(playerOneId, 0);
        playersMatchScore.put(playerTwoId, 0);
        setStage = new Set(playerOneId, playerTwoId);
    }

    public void calculateMatchScore(OngoingMatch match, Long id) {
        setStage.calculateScore(id);
        if (setStage.getState() != State.ON_GOING) {
            addWinPoints(id);
            if (playersMatchScore.get(id) == 2)
                match.setState(setStage.getState());
            else
                setStage = new Set(match.getPlayerOneId(), match.getPlayerTwoId());
        }
    }

    private void addWinPoints(Long id) {
        int currentScore = playersMatchScore.get(id);
        playersMatchScore.put(id, currentScore + 1);
    }
}
