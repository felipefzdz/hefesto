package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Optional;

public class CreateJob {

    private final UserClient userClient;
    private final JobFactory jobFactory;
    private final Jobs jobs;
    private final UserIdFactory userIdFactory;

    @Inject
    public CreateJob(UserClient userClient, JobFactory jobFactory, Jobs jobs, UserIdFactory userIdFactory) {
        this.userClient = userClient;
        this.jobFactory = jobFactory;
        this.jobs = jobs;
        this.userIdFactory = userIdFactory;
    }

    public Job with(String userId, String title) {
        Optional<User> user = userClient.create(userIdFactory.create(userId));
        if (user.isPresent() && user.get().isEmployer()){
            Job job = jobFactory.create(user.get(), title, jobs.nextId());
            jobs.add(job);
            return job;
        }
        throw new AuthorizationException("Only employers can create jobs");
    }
}
