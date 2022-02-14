package com.williangois.jokenpo.enumerations;

import java.io.IOException;
import java.util.stream.Stream;

public enum EnumMovement {

    ROCK("pedra"),
    PAPER("papel"),
    SCISSORS("tesoura");

    static { // Avoiding Illegal Forward Reference
        ROCK.setWeaknesses(EnumMovement.PAPER);
        PAPER.setWeaknesses(EnumMovement.SCISSORS);
        SCISSORS.setWeaknesses(EnumMovement.ROCK);
    }

    private final String name;
    private EnumMovement weaknesses;

    EnumMovement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public EnumMovement getWeaknesses() {
        return weaknesses;
    }

    private void setWeaknesses(EnumMovement weaknesses) {
        this.weaknesses = weaknesses;
    }

    public static EnumMovement getMovementByName(String name) throws IOException {
        return Stream.of(values())
                .filter(movement -> movement.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(IOException::new);
    }
}
