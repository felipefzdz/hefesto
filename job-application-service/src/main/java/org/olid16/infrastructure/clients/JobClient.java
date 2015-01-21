package org.olid16.infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.JobId;
import org.olid16.infrastructure.circuit_breaker.commands.GetJobByIdCommand;
import org.olid16.infrastructure.circuit_breaker.commands.GetJobByIdCommandFactory;
import org.olid16.infrastructure.clients.adapters.JobAdapter;

import java.util.Optional;

public class JobClient {
    private final JobAdapter jobAdapter;
    private final GetJobByIdCommandFactory factory;

    @Inject
    public JobClient(JobAdapter jobAdapter,
                     GetJobByIdCommandFactory factory) {
        this.jobAdapter = jobAdapter;
        this.factory = factory;
    }

    public Optional<org.olid16.domain.values.Job> create(JobId jobId) {
        GetJobByIdCommand getJobByIdCommand = factory.create(jobId.id());
        return getJobByIdCommand.execute().
                map(job -> Optional.of(jobAdapter.fromClient(job)))
                .orElse(Optional.empty());
    }
}
