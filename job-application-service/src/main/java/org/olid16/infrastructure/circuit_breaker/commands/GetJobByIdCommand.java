package org.olid16.infrastructure.circuit_breaker.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.yammer.tenacity.core.TenacityCommand;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.entities.Job;

import java.util.Optional;

import static org.olid16.infrastructure.circuit_breaker.JobApplicationServiceTenacityPropertyKey.USER_SERVICE;

public class GetJobByIdCommand extends TenacityCommand<Optional<Job>> {
    private final JobApi jobApi;
    private final String jobId;

    @Inject
    public GetJobByIdCommand(JobApi jobApi,
                             @Assisted String jobId) {
        super(USER_SERVICE);
        this.jobApi = jobApi;
        this.jobId = jobId;
    }

    @Override
    protected Optional<Job> run() throws Exception {
        return Optional.of(jobApi.getBy(jobId));
    }

    @Override
    protected Optional<Job> getFallback() {
        return Optional.empty();
    }
}
