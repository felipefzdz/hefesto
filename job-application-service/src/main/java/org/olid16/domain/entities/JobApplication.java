package org.olid16.domain.entities;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;

import java.util.Optional;

public class JobApplication {
    private final UserClient userClient;
    private final JobClient jobClient;
    private final JobApplicationId jobApplicationId;
    private final JobId jobId;
    private final UserId jobseekerId;
    private final ResumeId resumeId;

    @Inject
    public JobApplication(UserClient userClient,
                            JobClient jobClient,
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
    }

    public static JobApplication create(UserClient userClient,
                                        JobClient jobClient,
                                        JobApplicationId jobApplicationId, 
                                        JobId jobId, 
                                        UserId jobseekerId, 
                                        ResumeId resumeId) {
        return new JobApplication(userClient, jobClient, jobApplicationId, jobId, jobseekerId, resumeId);
    }

    public void validate() {
        Optional<User> user = userClient.create(UserId.create(jobseekerId()));
        if (user.isPresent() && user.get().isJobseeker()){
            Optional<Job> job = jobClient.create(JobId.create(jobId()));
            if(job.isPresent()){
                return;
            }
            throw new ValidationException(String.format("Job %s doesn't exist", jobId()));
        }
        throw new AuthorizationException("Only jobseekers can apply to jobs");
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
