package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;

import java.util.List;
import java.util.Optional;

public class GetJobs {
    private final Jobs jobs;

    @Inject
    public GetJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public List<Job> byEmployer(String employerId) {
        return jobs.byEmployerId(employerId);
    }

    public List<Job> all() {
        return jobs.all();
    }

    public List<Job> byJobseeker(String jobseekerId) {
        return jobs.byJobseekerId(jobseekerId);
    }

    public Optional<Job> byId(String id) {
        return jobs.byId(id);
    }
}
