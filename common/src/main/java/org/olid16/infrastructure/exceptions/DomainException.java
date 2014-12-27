package org.olid16.infrastructure.exceptions;

import java.io.IOException;

public class DomainException extends RuntimeException{

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Exception e) {
        super(message, e);
    }
}