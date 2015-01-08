package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.factories.UserFactory;

import java.util.Optional;

public class GetJobs {
    private final Jobs jobs;

    @Inject
    public GetJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public Optional<String> byEmployer(String employerId) {
        return jobs.byEmployerId(employerId);
    }

    public Optional<String> all() {
        return jobs.all();
    }
}
