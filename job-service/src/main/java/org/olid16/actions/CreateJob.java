package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.Optional;

public class CreateJob {

    private final UserFactory userFactory;
    private final JobFactory jobFactory;
    private final Jobs jobs;

    @Inject
    public CreateJob(UserFactory userFactory, JobFactory jobFactory, Jobs jobs) {
        this.userFactory = userFactory;
        this.jobFactory = jobFactory;
        this.jobs = jobs;
    }

    public Job with(JsonEntity jsonEntity) {
        Optional<User> user = userFactory.create(jsonEntity);
        if (user.isPresent() && user.get().isEmployer()){
            Job job = jobFactory.create(jsonEntity, jobs.nextId());
            jobs.add(job);
            return job;
        }
        throw new AuthorizationException("Only employers can create jobs");
    }
}
