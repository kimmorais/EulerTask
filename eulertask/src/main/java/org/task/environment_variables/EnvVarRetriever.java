package org.task.environment_variables;

import org.task.environment_variables.exceptions.EnvVarNotDefinedException;

import java.util.Optional;

import static org.task.environment_variables.EnvVarKeys.FULL_PATH_TO_POKER_FILE;

public class EnvVarRetriever {

    private EnvVarRetriever() {}

    public static String retrievePokerFileFullPath() {

        return Optional.ofNullable(System.getenv(FULL_PATH_TO_POKER_FILE))
                .orElseThrow(() -> new EnvVarNotDefinedException(FULL_PATH_TO_POKER_FILE));
    }
}
