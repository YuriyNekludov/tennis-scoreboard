package edu.tennis_scoreboard.mapper;

import edu.tennis_scoreboard.dto.PlayerDto;
import edu.tennis_scoreboard.model.Player;

public class PlayerMapper implements Mapper<Player, PlayerDto> {

    @Override
    public PlayerDto mapFrom(Player object) {
        return new PlayerDto(
                object.getId(),
                object.getName()
        );
    }
}
