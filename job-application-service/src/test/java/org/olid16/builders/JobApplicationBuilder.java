package org.olid16.builders;

import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

public class JobApplicationBuilder {

    private JobId jobId;
    private UserId jobseekerId;
    private JobApplicationId jobApplicationId;

    public JobApplicationBuilder(JobId jobId, UserId jobseekerId, JobApplicationId jobApplicationId) {
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.jobApplicationId = jobApplicationId;
    }

    public static JobApplicationBuilder aJobApplication(){
        return new JobApplicationBuilder(
                JobIdBuilder.aJobId().build(),
                JobseekerIdBuilder.aJobseekerId().build(),
                JobApplicationIdBuilder.aJobApplicationId().build());

    }
    
    public JobApplicationBuilder w(JobApplicationId jobApplicationId){
        this.jobApplicationId = jobApplicationId;
        return this;
        
    }

    public JobApplication build(){
        return JobApplication.create(jobApplicationId, jobId, jobseekerId);

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
