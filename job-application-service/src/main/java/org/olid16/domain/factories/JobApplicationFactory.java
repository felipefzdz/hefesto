package org.olid16.domain.factories;

import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public interface JobApplicationFactory {
    
    public JobApplication create(JobApplicationId jobApplicationId, JobId jobId, UserId jobseekerId, ResumeId resumeId);
}
