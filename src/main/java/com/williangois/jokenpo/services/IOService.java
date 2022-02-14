package com.williangois.jokenpo.services;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.helpers.CommandLineParser;

import java.io.IOException;

public interface IOService {

    String INVALID_MOVEMENT_MESSAGE = "Nome da jogada %s é inválido (utilize 'pedra', 'papel' e 'tesoura').";
    String MISSING_ARGUMENT_MESSAGE = "O argumento '%s' não foi informado!";

    GameMatch prepareMatch(CommandLineParser parser) throws IOException;

    String getMatchResult(GameMatchResponse response);
}
