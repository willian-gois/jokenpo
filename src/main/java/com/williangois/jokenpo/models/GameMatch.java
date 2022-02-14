package com.williangois.jokenpo.models;

import com.williangois.jokenpo.enumerations.EnumMovement;

public class GameMatch {

    private final String playerNameA;
    private final String playerNameB;
    private final EnumMovement movementPlayerA;
    private final EnumMovement movementPlayerB;

    public GameMatch(String playerNameA, String playerNameB, EnumMovement movementPlayerA, EnumMovement movementPlayerB) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.movementPlayerA = movementPlayerA;
        this.movementPlayerB = movementPlayerB;
    }

    public String getPlayerNameA() {
        return playerNameA;
    }

    public String getPlayerNameB() {
        return playerNameB;
    }

    public EnumMovement getMovementPlayerA() {
        return movementPlayerA;
    }

    public EnumMovement getMovementPlayerB() {
        return movementPlayerB;
    }
}
