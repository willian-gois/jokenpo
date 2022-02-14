package com.williangois.jokenpo.dto;

public class GameMatchResponse {

    private final String winner;

    public GameMatchResponse(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }
}
