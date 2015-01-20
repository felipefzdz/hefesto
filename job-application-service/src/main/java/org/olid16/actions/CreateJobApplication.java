package org.olid16.actions;

import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;

import javax.inject.Inject;
import java.util.Optional;

public class CreateJobApplication {
    private final JobApplications jobApplications;
    private final UserClient userClient;
    private final JobClient jobClient;

    @Inject
    public CreateJobApplication(JobApplications jobApplications, 
                                UserClient userClient, 
                                JobClient jobClient) {
        this.jobApplications = jobApplications;
        this.userClient = userClient;
        this.jobClient = jobClient;
    }

    public JobApplication with(JobApplication jobApplication) {
        Optional<User> user = userClient.create(UserId.create(jobApplication.jobseekerId()));
        if (user.isPresent() && user.get().isJobseeker()){
            Optional<Job> job = jobClient.create(JobId.create(jobApplication.jobId()));
            if(job.isPresent()){
                return jobApplications.add(jobApplication);
            }
            throw new ValidationException(String.format("Job %s doesn't exist", jobApplication.jobId()));
        }
        throw new AuthorizationException("Only jobseekers can apply to jobs");

    }
}
