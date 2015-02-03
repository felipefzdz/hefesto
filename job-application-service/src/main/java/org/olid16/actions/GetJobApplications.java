package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;

import java.util.List;

public class GetJobApplications {
    private final JobApplications jobApplications;

    @Inject
    public GetJobApplications(JobApplications jobApplications) {
        this.jobApplications = jobApplications;
    }

    public List<JobApplication> byJobseeker(String jobseekerId) {
        return jobApplications.byJobseekerId(jobseekerId);
    }
}
