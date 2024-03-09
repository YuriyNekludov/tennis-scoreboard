package edu.tennis_scoreboard.mapper;

import edu.tennis_scoreboard.dto.MatchDto;
import edu.tennis_scoreboard.model.Match;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatchMapper implements Mapper<Match, MatchDto>{

    @Override
    public MatchDto mapFrom(Match object) {
        return new MatchDto(
                object.getId(),
                object.getPlayerOne().getName(),
                object.getPlayerTwo().getName(),
                object.getWinner().getName()
        );
    }
}
