import edu.tennis_scoreboard.model.ongoing_match.match_stage.game.Game;
import edu.tennis_scoreboard.model.ongoing_match.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    private final Game game = new Game(1L, 2L);

    @Test
    void playerOneShouldWinWithDefaultScore() {
        while(game.getState() == State.ON_GOING)
            game.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, game.getState());
    }

    @Test
    void gameShouldBeContinued() {
        for (int i = 0; i < 10; i++) {
            game.calculateScore(1L);
            game.calculateScore(2L);
        }
        Assertions.assertEquals(State.ON_GOING, game.getState());
    }

    @Test
    void playerOneShouldWinGameInDeuse() {
        for (int i = 0; i < 10; i++) {
            game.calculateScore(1L);
            game.calculateScore(2L);
        }
        game.calculateScore(1L);
        game.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, game.getState());
    }

    @Test
    void playerOneShouldWinInNonNullScore() {
        for (int i = 0; i < 2; i++) {
            game.calculateScore(1L);
            game.calculateScore(2L);
        }
        game.calculateScore(1L);
        game.calculateScore(1L);
        Assertions.assertEquals(State.PLAYER_ONE_WIN, game.getState());
    }
}
