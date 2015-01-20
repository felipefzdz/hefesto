package org.olid16.infrastructure.rest.adapters;

import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.entities.JobApplication;

public class JobApplicationAdapter {
    public org.olid16.domain.entities.JobApplication toDomain(JobApplication jobApplication) {
        return org.olid16.domain.entities.JobApplication.create(
                jobApplicationIdFrom(jobApplication),
                jobIdFrom(jobApplication),
                jobseekerIdFrom(jobApplication)
                
        );
    }

    public JobApplication fromDomain(org.olid16.domain.entities.JobApplication createdJobApplication) {
        return new JobApplication(createdJobApplication.jobId(), createdJobApplication.jobseekerId(), createdJobApplication.id());
    }

    private UserId jobseekerIdFrom(JobApplication jobApplication) {
        return UserId.create(jobApplication.getJobseekerId());
    }

    private JobId jobIdFrom(JobApplication jobApplication) {
        return JobId.create(jobApplication.getJobId());
    }

    private JobApplicationId jobApplicationIdFrom(JobApplication jobApplication) {
        String jobApplicationId = jobApplication.getId() == null ? "" : jobApplication.getId();
        return JobApplicationId.create(jobApplicationId);
    }
}
