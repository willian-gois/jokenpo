package com.williangois.jokenpo.services;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;

public interface GameMatchService {

    GameMatchResponse play(GameMatch gameMatch);
}
