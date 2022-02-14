package com.williangois.jokenpo.services.impl;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.enumerations.EnumMovement;
import com.williangois.jokenpo.helpers.CommandLineParser;
import com.williangois.jokenpo.services.IOService;

import java.io.IOException;

public class IOServiceImpl implements IOService {

    @Override
    public GameMatch prepareMatch(CommandLineParser parser) throws IOException {
        EnumMovement movementA = tryGetMovement(parser, "jogadaA");
        if (movementA == null) throw new IOException(String.format(INVALID_MOVEMENT_MESSAGE, "A"));

        EnumMovement movementB = tryGetMovement(parser, "jogadaB");
        if (movementB == null) throw new IOException(String.format(INVALID_MOVEMENT_MESSAGE, "B"));

        String playerNameA = parser.getAsString("jogadorA");
        if (playerNameA == null) throw new IOException(String.format(MISSING_ARGUMENT_MESSAGE, "jogadorA"));

        String playerNameB = parser.getAsString("jogadorB");
        if (playerNameB == null) throw new IOException(String.format(MISSING_ARGUMENT_MESSAGE, "jogadorB"));

        return new GameMatch(playerNameA, playerNameB, movementA, movementB);
    }

    @Override
    public String getMatchResult(GameMatchResponse response) {
        String winner = response.getWinner();

        if (winner == null) {
            return "A partida deu empate!";
        } else {
            return String.format("O vencedor da partida foi %s!", winner);
        }
    }

    private EnumMovement tryGetMovement(CommandLineParser parser, String key) throws IOException {
        String movementName = parser.getAsString(key);

        if (movementName == null) throw new IOException(String.format(MISSING_ARGUMENT_MESSAGE, key));

        try {
            return EnumMovement.getMovementByName(movementName);
        } catch (IOException exception) {
            return null;
        }
    }
}
