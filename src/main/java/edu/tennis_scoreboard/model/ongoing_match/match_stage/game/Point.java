package edu.tennis_scoreboard.model.ongoing_match.match_stage.game;

enum Point {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    final String value;

    Point(String value) {
        this.value = value;
    }
}
