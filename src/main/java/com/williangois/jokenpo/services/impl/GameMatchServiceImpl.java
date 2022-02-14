package com.williangois.jokenpo.services.impl;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.enumerations.EnumMovement;
import com.williangois.jokenpo.services.GameMatchService;

public class GameMatchServiceImpl implements GameMatchService {

    @Override
    public GameMatchResponse play(GameMatch gameMatch) {
        EnumMovement movementA = gameMatch.getMovementPlayerA();
        EnumMovement movementB = gameMatch.getMovementPlayerB();
        String playerWinner;

        if (movementA.equals(movementB)) {
            playerWinner = null;
        } else if (movementB.getWeaknesses().equals(movementA)) {
            playerWinner = gameMatch.getPlayerNameA();
        } else {
            playerWinner = gameMatch.getPlayerNameB();
        }

        return new GameMatchResponse(playerWinner);
    }
}
