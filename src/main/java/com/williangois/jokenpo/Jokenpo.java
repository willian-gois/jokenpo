package com.williangois.jokenpo;

import com.williangois.jokenpo.dto.GameMatchResponse;
import com.williangois.jokenpo.models.GameMatch;
import com.williangois.jokenpo.helpers.CommandLineParser;
import com.williangois.jokenpo.services.GameMatchService;
import com.williangois.jokenpo.services.IOService;
import com.williangois.jokenpo.services.impl.GameMatchServiceImpl;
import com.williangois.jokenpo.services.impl.IOServiceImpl;

import java.io.IOException;

public class Jokenpo {

	private static final IOService ioService = new IOServiceImpl();
	private static final GameMatchService gameMatchService = new GameMatchServiceImpl();

	public static void main(String[] args) {
		try {
			GameMatch gameMatch = ioService.prepareMatch(new CommandLineParser(args));
			GameMatchResponse response = gameMatchService.play(gameMatch);

			System.out.println(ioService.getMatchResult(response));
		} catch (IOException e) {
			System.err.printf("ERRO: %s%n", e.getMessage());
		}
	}

}
