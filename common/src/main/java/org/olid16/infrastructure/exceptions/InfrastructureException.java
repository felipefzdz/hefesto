package org.olid16.infrastructure.exceptions;

public class InfrastructureException extends RuntimeException {
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
