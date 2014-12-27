package org.olid16.infrastructure.exceptions;

public class DomainException extends RuntimeException{

    public DomainException(String message) {
        super(message);
    }
}