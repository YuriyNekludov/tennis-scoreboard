package edu.tennis_scoreboard.dto;

public record MatchDto(Long id,
                       String playerOneName,
                       String playerTwoName,
                       String winnerName) {
}
