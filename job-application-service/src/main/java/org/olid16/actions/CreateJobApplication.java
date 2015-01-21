package org.olid16.actions;

import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;

import javax.inject.Inject;

public class CreateJobApplication {
    private final JobApplications jobApplications;

    @Inject
    public CreateJobApplication(JobApplications jobApplications) {
        this.jobApplications = jobApplications;
    }

    public JobApplication with(JobApplication jobApplication) {
        jobApplication.validate();
        return jobApplications.add(jobApplication);

    }
}
