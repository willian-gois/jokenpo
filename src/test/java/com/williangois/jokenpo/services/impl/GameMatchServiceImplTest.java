package com.williangois.jokenpo.services.impl;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.enumerations.EnumMovement;
import com.williangois.jokenpo.services.GameMatchService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameMatchServiceImplTest {

	private static GameMatchService gameMatchService;

	@BeforeAll
	static void setup() {
		gameMatchService = new GameMatchServiceImpl();
	}

	@Test
	void shouldGameResultADrawInAllEqualsMovements() {
		Stream.of(EnumMovement.values()).forEach(movement -> {
			GameMatch gameMatch = createGameMatch(movement, movement);
			GameMatchResponse response = gameMatchService.play(gameMatch);

			assertNull(response.getWinner());
		});
	}

	@Test
	void shouldRockWinTheMatch() {
		GameMatch gameMatch = createGameMatch(EnumMovement.ROCK, EnumMovement.SCISSORS);
		GameMatchResponse response = gameMatchService.play(gameMatch);

		assertEquals("John Doe", response.getWinner());
	}

	@Test
	void shouldPaperWinTheMatch() {
		GameMatch gameMatch = createGameMatch(EnumMovement.PAPER, EnumMovement.ROCK);
		GameMatchResponse response = gameMatchService.play(gameMatch);

		assertEquals("John Doe", response.getWinner());
	}

	@Test
	void shouldScissorsWinTheMatch() {
		GameMatch gameMatch = createGameMatch(EnumMovement.SCISSORS, EnumMovement.PAPER);
		GameMatchResponse response = gameMatchService.play(gameMatch);

		assertEquals("John Doe", response.getWinner());
	}

	@Test
	void shouldScissorsWinTheMatchReverse() {
		GameMatch gameMatch = createGameMatch(EnumMovement.PAPER, EnumMovement.SCISSORS);
		GameMatchResponse response = gameMatchService.play(gameMatch);

		assertEquals("Jane Doe", response.getWinner());
	}

	GameMatch createGameMatch(EnumMovement movementA, EnumMovement movementB) {
		return new GameMatch(
				"John Doe",
				"Jane Doe",
				movementA,
				movementB
		);
	}
}
