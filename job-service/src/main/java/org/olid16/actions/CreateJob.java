package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;
import org.olid16.domain.services.UserService;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.rest.JsonEntity;

public class CreateJob {

    private final UserService userService;
    private final JobFactory jobFactory;
    private final Jobs jobs;

    @Inject
    public CreateJob(UserService userService, JobFactory jobFactory, Jobs jobs) {
        this.userService = userService;
        this.jobFactory = jobFactory;
        this.jobs = jobs;
    }

    public Job with(JsonEntity jsonEntity) {
        if (userService.isEmployer(jsonEntity)){
            Job job = jobFactory.create(jsonEntity, jobs.nextId());
            jobs.add(job);
            return job;
        }
        throw new AuthorizationException("Only employers can create jobs");
    }
}
