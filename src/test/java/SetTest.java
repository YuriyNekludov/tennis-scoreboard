import edu.tennis_scoreboard.model.ongoing_match.State;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.MatchStage;
import edu.tennis_scoreboard.model.ongoing_match.match_stage.set.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetTest {
    private final MatchStage<Integer> set = new Set(1L, 2L);

    @Test
    void setShouldBeContinued() {
        for (int i = 0; i < 23; i++)
            set.calculateScore(1L);
        for (int i = 0; i < 23; i++)
            set.calculateScore(2L);
        for (int i = 0; i < 24; i ++) {
            set.calculateScore(1L);
            set.calculateScore(2L);
        }
        Assertions.assertEquals(State.ON_GOING, set.getState());
    }

    @Test
    void playerOneShouldWinInTieBreak() {
        for (int i = 0; i < 23; i++)
            set.calculateScore(1L);
        for (int i = 0; i < 23; i++)
            set.calculateScore(2L);
        for (int i = 0; i < 23; i ++) {
            set.calculateScore(1L);
            set.calculateScore(2L);
        }
        while (set.getState() == State.ON_GOING)
            set.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, set.getState());
    }

    @Test
    void playerOneShouldWinWithDefaultScore() {
        while (set.getState() == State.ON_GOING)
            set.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, set.getState());
    }

    @Test
    void playerOneShouldWinInNonNullScore() {
        for (int i = 0; i < 21; i++)
            set.calculateScore(2L);
        while (set.getState() == State.ON_GOING)
            set.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, set.getState());
    }
}
