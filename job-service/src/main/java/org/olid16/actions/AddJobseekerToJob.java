package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Optional;

public class AddJobseekerToJob {
    private final Jobs jobs;
    private final UserIdFactory userIdFactory;
    private final UserClient userClient;

    @Inject
    public AddJobseekerToJob(Jobs jobs, UserIdFactory userIdFactory, UserClient userClient) {
        this.jobs = jobs;
        this.userIdFactory = userIdFactory;
        this.userClient = userClient;
    }


    public void with(String userIdValue, String jobId) {
        UserId userId = userIdFactory.create(userIdValue);
        Optional<User> user = userClient.create(userId);
        if(user.isPresent() & user.get().isJobseeker()){
            jobs.addJobseeker(JobId.create(jobId), userId);
            return;
        }
        throw new AuthorizationException("Only jobseekers can be added into a job");
    }
}
