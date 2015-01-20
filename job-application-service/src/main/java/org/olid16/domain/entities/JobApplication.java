package org.olid16.domain.entities;

import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

public class JobApplication {
    private final JobApplicationId jobApplicationId;
    private final JobId jobId;
    private final UserId jobseekerId;

    private JobApplication(JobApplicationId jobApplicationId, 
                           JobId jobId, 
                           UserId jobseekerId) {
        this.jobApplicationId = jobApplicationId;
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
    }

    public static JobApplication create(JobApplicationId jobApplicationId, JobId jobId, UserId jobseekerId) {
        return new JobApplication(jobApplicationId, jobId, jobseekerId);
    }

    public String jobId() {
        return jobId.id();
    }

    public String jobseekerId() {
        return jobseekerId.id();
    }


    public String id() {
        return jobApplicationId.id();
    }

}
