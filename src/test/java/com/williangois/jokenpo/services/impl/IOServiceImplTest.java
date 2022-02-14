package com.williangois.jokenpo.services.impl;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.helpers.CommandLineParser;
import com.williangois.jokenpo.services.GameMatchService;
import com.williangois.jokenpo.services.IOService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IOServiceImplTest {

	private static IOService ioService;
	private static GameMatchService gameMatchService;

	@BeforeAll
	static void setup() {
		ioService = new IOServiceImpl();
		gameMatchService = new GameMatchServiceImpl();
	}

	@Test
	void shouldMissingArgumentJogadaBInParser() {
		CommandLineParser parser = ArgumentsBuilder.of()
				.withPlayerA("John Doe")
				.withMovementA("papel")
				.withPlayerB("Jane Doe")
				.buildParser();

		IOException exception = assertThrows(IOException.class, () -> ioService.prepareMatch(parser));
		assertEquals(String.format(IOService.MISSING_ARGUMENT_MESSAGE, "jogadaB"), exception.getMessage());
	}

	@Test
	void shouldArgumentJogadaABeInvalid() {
		CommandLineParser parser = ArgumentsBuilder.of()
				.withPlayerA("John Doe")
				.withMovementA("cascalho")
				.withPlayerB("Jane Doe")
				.withMovementB("pedra")
				.buildParser();

		IOException exception = assertThrows(IOException.class, () -> ioService.prepareMatch(parser));
		assertEquals(String.format(IOService.INVALID_MOVEMENT_MESSAGE, "A"), exception.getMessage());
	}

	@Test
	void shouldArgumentJogadaBBeInvalid() {
		CommandLineParser parser = ArgumentsBuilder.of()
				.withPlayerA("John Doe")
				.withMovementA("papel")
				.withPlayerB("Jane Doe")
				.withMovementB("faca")
				.buildParser();

		IOException exception = assertThrows(IOException.class, () -> ioService.prepareMatch(parser));
		assertEquals(String.format(IOService.INVALID_MOVEMENT_MESSAGE, "B"), exception.getMessage());
	}

	@Test
	void shouldMatchResultBeDraw() {
		CommandLineParser parser = ArgumentsBuilder.of()
				.withPlayerA("John Doe")
				.withMovementA("papel")
				.withPlayerB("Jane Doe")
				.withMovementB("papel")
				.buildParser();

		try {
			GameMatch gameMatch = ioService.prepareMatch(parser);
			GameMatchResponse response = gameMatchService.play(gameMatch);

			assertEquals("A partida deu empate!", ioService.getMatchResult(response));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void shouldMatchResultBePlayerWin() {
		CommandLineParser parser = ArgumentsBuilder.of()
				.withPlayerA("John Doe")
				.withMovementA("papel")
				.withPlayerB("Jane Doe")
				.withMovementB("tesoura")
				.buildParser();

		try {
			GameMatch gameMatch = ioService.prepareMatch(parser);
			GameMatchResponse response = gameMatchService.play(gameMatch);

			assertEquals("O vencedor da partida foi Jane Doe!", ioService.getMatchResult(response));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class ArgumentsBuilder {

		private StringBuilder stringBuilder = new StringBuilder();

		public static ArgumentsBuilder of() {
			return new ArgumentsBuilder();
		}

		public ArgumentsBuilder withPlayerA(String name) {
			return append(this.stringBuilder.append("-jogadorA ").append(name));
		}

		public ArgumentsBuilder withPlayerB(String name) {
			return append(this.stringBuilder.append("-jogadorB ").append(name));
		}

		public ArgumentsBuilder withMovementA(String name) {
			return append(this.stringBuilder.append("-jogadaA ").append(name));
		}

		public ArgumentsBuilder withMovementB(String name) {
			return append(this.stringBuilder.append("-jogadaB ").append(name));
		}

		private ArgumentsBuilder append(StringBuilder stringBuilder) {
			this.stringBuilder = stringBuilder.append(" ");
			return this;
		}

		public String[] build() {
			return this.stringBuilder.toString().split(" ");
		}

		public CommandLineParser buildParser() {
			return new CommandLineParser(build());
		}
	}
}
