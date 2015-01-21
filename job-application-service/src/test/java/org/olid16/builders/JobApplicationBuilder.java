package org.olid16.builders;

import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;

public class JobApplicationBuilder {

    private JobId jobId;
    private UserId jobseekerId;
    private JobApplicationId jobApplicationId;
    private ResumeId resumeId;
    private UserClient userClient;
    private JobClient jobClient;
    private Resumes resumes;

    public JobApplicationBuilder(JobId jobId,
                                 UserId jobseekerId,
                                 JobApplicationId jobApplicationId,
                                 ResumeId resumeId,
                                 UserClient userClient,
                                 JobClient jobClient,
                                 Resumes resumes) {
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.jobApplicationId = jobApplicationId;
        this.resumeId = resumeId;
        this.userClient = userClient;
        this.jobClient = jobClient;
        this.resumes = resumes;
    }

    public static JobApplicationBuilder aJobApplication(){
        return new JobApplicationBuilder(
                JobIdBuilder.aJobId().build(),
                JobseekerIdBuilder.aJobseekerId().build(),
                JobApplicationIdBuilder.aJobApplicationId().build(),
                ResumeIdBuilder.aResumeId().build(), 
                null, 
                null, 
                null);

    }
    
    public JobApplicationBuilder w(JobApplicationId jobApplicationId){
        this.jobApplicationId = jobApplicationId;
        return this;
    }
    
    public JobApplicationBuilder w(UserClient userClient){
        this.userClient = userClient;
        return this;
    }

    public JobApplicationBuilder w(JobClient jobClient){
        this.jobClient = jobClient;
        return this;
    }

    public JobApplicationBuilder w(Resumes resumes){
        this.resumes = resumes;
        return this;
    }

    public JobApplication build(){
        return new JobApplication(userClient, jobClient, resumes, jobApplicationId, jobId, jobseekerId, resumeId);

    }

    private static class JobseekerIdBuilder {
        private final String id;

        private JobseekerIdBuilder(String id) {
            this.id = id;
        }

        public static JobseekerIdBuilder aJobseekerId() {
            return new JobseekerIdBuilder("1234");
        }

        public UserId build() {
            return UserId.create(id);
        }
    }

    private static class JobIdBuilder {
        private final String id;

        private JobIdBuilder(String id) {
            this.id = id;
        }

        public static JobIdBuilder aJobId() {
            return new JobIdBuilder("1234");
        }

        public JobId build() {
            return JobId.create(id);
        }
    }

    private static class ResumeIdBuilder {
        private final String id;

        private ResumeIdBuilder(String id) {
            this.id = id;
        }

        public static ResumeIdBuilder aResumeId() {
            return new ResumeIdBuilder("1234");
        }

        public ResumeId build() {
            return ResumeId.create(id);
        }
    }

    private static class JobApplicationIdBuilder {
        private final String id;

        private JobApplicationIdBuilder(String id) {
            this.id = id;
        }

        public static JobApplicationIdBuilder aJobApplicationId() {
            return new JobApplicationIdBuilder("1234");
        }

        public JobApplicationId build() {
            return JobApplicationId.create(id);
        }
    }


}
