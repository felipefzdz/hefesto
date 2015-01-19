package org.olid16.infrastructure.circuit_breaker.commands;

public interface GetUserByIdCommandFactory {
    
    public GetUserByIdCommand create(String userId);
}
