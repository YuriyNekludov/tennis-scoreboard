package edu.tennis_scoreboard.repository;

import edu.tennis_scoreboard.model.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Match> {

    public MatchRepository(Session session) {
        super(Match.class, session);
    }

    public List<Match> getByName(String name) {
        return session.createQuery("select match from Match match " +
                        "join match.playerOne playerOne " +
                        "join match.playerTwo playerTwo " +
                        "join match.winner winner " +
                        "where (playerOne.name = :name " +
                        "or playerTwo.name = :name " +
                        "or winner.name = :name)", Match.class)
                .setParameter("name", name)
                .list();
    }
}
