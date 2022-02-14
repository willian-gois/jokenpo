package com.williangois.jokenpo.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Implementação simplificada de um analisador de CLI.
 */
public class CommandLineParser {

    private static final String KEY_SPECIFIER = "-";

    private final Map<String, List<String>> parsedArgs;

    public CommandLineParser(String[] args) {
        this.parsedArgs = new HashMap<>();

        parse(args);
    }

    public Map<String, List<String>> getParsedArgs() {
        return parsedArgs;
    }

    public List<String> get(String key) {
        return parsedArgs.get(key.toLowerCase());
    }

    public String getAsString(String key) {
        List<String> value = get(key);
        return value == null ? null : String.join(" ", value);
    }

    private void parse(String[] args) {
        AtomicReference<String> lastArgumentKey = new AtomicReference<>();

        Stream.of(args).forEach(argument -> {
            if (argument.startsWith(KEY_SPECIFIER)) {
                argument = argument.substring(1).toLowerCase();

                lastArgumentKey.set(argument);
                parsedArgs.put(argument, new ArrayList<>());
            } else {
                parsedArgs.get(lastArgumentKey.get()).add(argument);
            }
        });
    }
}
