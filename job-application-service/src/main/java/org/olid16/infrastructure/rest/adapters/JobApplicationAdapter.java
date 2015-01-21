package org.olid16.infrastructure.rest.adapters;

import com.google.inject.Inject;
import org.olid16.domain.factories.JobApplicationFactory;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.entities.JobApplication;

public class JobApplicationAdapter {
    private final JobApplicationFactory jobApplicationFactory;

    @Inject
    public JobApplicationAdapter(JobApplicationFactory jobApplicationFactory) {
        this.jobApplicationFactory = jobApplicationFactory;
    }

    public org.olid16.domain.entities.JobApplication toDomain(JobApplication jobApplication) {
        return jobApplicationFactory.create(
                jobApplicationIdFrom(jobApplication),
                jobIdFrom(jobApplication),
                jobseekerIdFrom(jobApplication),
                resumeIdFrom(jobApplication));
    }

    public JobApplication fromDomain(org.olid16.domain.entities.JobApplication jobApp) {
        return new JobApplication(jobApp.jobId(),
                jobApp.jobseekerId(),
                jobApp.id(),
                jobApp.resumeId());
    }

    private ResumeId resumeIdFrom(JobApplication jobApplication) {
        return ResumeId.create(jobApplication.getResumeId());
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
