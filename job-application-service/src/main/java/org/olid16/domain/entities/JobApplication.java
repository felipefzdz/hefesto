package org.olid16.domain.entities;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;

public class JobApplication {
    private final UserClient userClient;
    private final JobClient jobClient;
    private final JobApplicationId jobApplicationId;
    private final JobId jobId;
    private final UserId jobseekerId;
    private final ResumeId resumeId;
    private final Resumes resumes;

    @Inject
    public JobApplication(UserClient userClient,
                          JobClient jobClient,
                          Resumes resumes,
                          @Assisted JobApplicationId jobApplicationId,
                          @Assisted JobId jobId,
                          @Assisted UserId jobseekerId,
                          @Assisted ResumeId resumeId) {
        this.userClient = userClient;
        this.jobClient = jobClient;
        this.jobApplicationId = jobApplicationId;
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.resumeId = resumeId;
        this.resumes = resumes;
    }

    public void validate() {
        validateUser();
        validateJob();

    }

    private void validateUser() {
        User user = userClient.create(jobseekerId).orElseThrow(() -> new AuthorizationException(String.format("User %s doesn't exist", jobseekerId())));
        if (!user.isJobseeker()) {
            throw new AuthorizationException("Only jobseekers can apply to jobs");
        } 
    }

    private void validateJob() {
        Job job = jobClient.create(jobId).orElseThrow(() -> new ValidationException(String.format("Job %s doesn't exist", jobId())));
        if (JobType.JREQ.equals(job.jobType())){
            validateResume();
        }
    }

    private void validateResume() {
        Resume resume = resumes.findById(resumeId()).
                orElseThrow(() -> new ValidationException(String.format("Resume %s doesn't exist and it's needed for apply to JREQ jobs", resumeId())));
        if(!resume.userId().equals(jobseekerId())){
            throw new ValidationException(String.format("Resume %s doesn't belong to jobseeker %s", resumeId(), jobseekerId()));
        }
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

    public String resumeId() {
        return resumeId.id();
    }
}
