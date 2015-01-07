package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.List;
import java.util.Optional;

public class GetJobs {
    private final UserFactory userFactory;
    private final Jobs jobs;

    @Inject
    public GetJobs(UserFactory userFactory, Jobs jobs) {
        this.userFactory = userFactory;
        this.jobs = jobs;
    }

    public List<Job> with(JsonEntity jsonEntity) {
        Optional<User> user = userFactory.create(jsonEntity);
        if (user.isPresent() && user.get().isEmployer()){
            return jobs.by(user.get().userId());
        }
        throw new AuthorizationException("Only employers can get jobs");
    }
}
