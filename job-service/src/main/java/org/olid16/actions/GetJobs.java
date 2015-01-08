package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.factories.UserFactory;

import java.util.Optional;

public class GetJobs {
    private final UserFactory userFactory;
    private final Jobs jobs;

    @Inject
    public GetJobs(UserFactory userFactory, Jobs jobs) {
        this.userFactory = userFactory;
        this.jobs = jobs;
    }

    public Optional<String> byEmployerId(String employerId) {
        return jobs.byEmployerId(employerId);
    }
}
