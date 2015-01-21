package org.olid16.infrastructure.circuit_breaker.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.yammer.tenacity.core.TenacityCommand;
import org.olid16.domain.values.JobType;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.entities.Job;
import org.olid16.infrastructure.clients.entities.User;

import static org.olid16.infrastructure.circuit_breaker.JobApplicationServiceTenacityPropertyKey.USER_SERVICE;

public class GetJobByIdCommand extends TenacityCommand<Job> {
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
    protected Job run() throws Exception {
        return jobApi.getBy(jobId);
    }

    @Override
    protected Job getFallback() {
        return new Job("", "", JobType.UNKNOWN.toString());
    }
}
