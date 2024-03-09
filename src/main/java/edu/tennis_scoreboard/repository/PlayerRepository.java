package edu.tennis_scoreboard.repository;

import edu.tennis_scoreboard.model.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Player> {

    public PlayerRepository(Session session) {
        super(Player.class, session);
    }

    public Optional<Player> getByName(String name) {
        return session.createQuery("select p from Player p " +
                        "where p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResultOptional();
    }
}
