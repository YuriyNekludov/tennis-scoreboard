package edu.tennis_scoreboard.servicie;

import edu.tennis_scoreboard.dto.MatchDto;
import edu.tennis_scoreboard.exception.InternalServerException;
import edu.tennis_scoreboard.mapper.MatchMapper;
import edu.tennis_scoreboard.model.Match;
import edu.tennis_scoreboard.model.ongoing_match.OngoingMatch;
import edu.tennis_scoreboard.repository.MatchRepository;
import edu.tennis_scoreboard.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;

@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {

    private final Session session;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final MatchMapper matchMapper;
    private final int MAX_LIST_SIZE = 15;

    public synchronized void saveFinishedMatch(OngoingMatch match) {
        try {
            session.beginTransaction();
            matchRepository.save(
                    Match.builder()
                            .playerOne(playerRepository.get(match.getPlayerOneId()))
                            .playerTwo(playerRepository.get(match.getPlayerTwoId()))
                            .winner(playerRepository.get(match.getWinnerId()))
                            .build());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new InternalServerException();
        }
    }

    public synchronized List<MatchDto> getMatchesByPlayerName(String name, int page) {
        try {
            session.beginTransaction();
            List<MatchDto> matches = matchRepository.getByName(name)
                    .stream()
                    .skip((page - 1) * MAX_LIST_SIZE)
                    .limit(MAX_LIST_SIZE)
                    .map(matchMapper::mapFrom)
                    .toList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new InternalServerException();
        }
    }

    public synchronized List<MatchDto> getAllMatches(int page) {
        try {
            session.beginTransaction();
            List<MatchDto> matches = matchRepository.findAll()
                    .stream()
                    .skip((page - 1) * MAX_LIST_SIZE)
                    .limit(MAX_LIST_SIZE)
                    .map(matchMapper::mapFrom)
                    .toList();
            session.getTransaction().commit();
            return matches;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new InternalServerException();
        }
    }
}
