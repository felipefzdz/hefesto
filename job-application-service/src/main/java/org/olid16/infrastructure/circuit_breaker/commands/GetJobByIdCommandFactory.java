package org.olid16.infrastructure.circuit_breaker.commands;

public interface GetJobByIdCommandFactory {

    public GetJobByIdCommand create(String jobId);
}
