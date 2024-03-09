package edu.tennis_scoreboard.servicie;

import edu.tennis_scoreboard.dto.PlayerDto;
import edu.tennis_scoreboard.exception.InternalServerException;
import edu.tennis_scoreboard.mapper.PlayerMapper;
import edu.tennis_scoreboard.model.Player;
import edu.tennis_scoreboard.model.ongoing_match.OngoingMatch;
import edu.tennis_scoreboard.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class OngoingMatchesService {

    private final Session session;
    private final PlayerRepository playerRepository;
    private final Map<UUID, OngoingMatch> ongoingMatches;
    private final PlayerMapper playerMapper;

    public synchronized OngoingMatch getOngoingMatchByUuid(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public synchronized UUID addNewOngoingMatch(PlayerDto playerOne, PlayerDto playerTwo) {
        OngoingMatch newMatch = new OngoingMatch(
                playerOne.id(),
                playerTwo.id(),
                playerOne.name(),
                playerTwo.name()
        );
        ongoingMatches.put(newMatch.getId(), newMatch);
        return newMatch.getId();
    }

    public synchronized void deleteFinishedMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }

    public synchronized PlayerDto getPlayerOrCreateNew(String name) {
        try {
            session.beginTransaction();
            Player player = playerRepository.getByName(name).orElseGet(() ->
                    playerRepository.save(
                            Player.builder()
                                    .name(name)
                                    .build()));
            session.getTransaction().commit();
            return playerMapper.mapFrom(player);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new InternalServerException();
        }
    }
}
