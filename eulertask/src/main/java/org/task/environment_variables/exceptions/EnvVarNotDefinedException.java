package org.task.environment_variables.exceptions;

public class EnvVarNotDefinedException extends RuntimeException {

    private static final String ENVIRONMENT_VARIABLE_NOT_DEFINED = "The environment variable %s was not defined. Please define it and try again.";

    public EnvVarNotDefinedException(String envVarKey){

        super(String.format(ENVIRONMENT_VARIABLE_NOT_DEFINED, envVarKey));
    }
}
