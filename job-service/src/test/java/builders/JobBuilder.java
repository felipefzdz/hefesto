package builders;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

public class JobBuilder {

    private JobId jobId;

    public JobBuilder(JobId jobId) {
        this.jobId = jobId;
    }

    public static JobBuilder aJob(){ 
        return new JobBuilder(JobIdBuilder.aJobId().build());
    }
    
    public Job build(){return new Job(jobId);}

    public static class JobIdBuilder {
        private final String id;

        public JobIdBuilder(String id) {
            this.id = id;
        }

        public static JobIdBuilder aJobId() {
            return new JobIdBuilder("1234");
        }

        public JobId build() {
            return JobId.create(id);
        }
    }
}
