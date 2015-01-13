package builders;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;

import static builders.JobBuilder.JobIdBuilder.*;
import static builders.JobBuilder.TitleBuilder.aTitle;
import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.aUserId;

public class JobBuilder {

    private JobId jobId;
    private User user;
    private Title title;

    public JobBuilder(JobId jobId, User user, Title title) {
        this.jobId = jobId;
        this.user = user;
        this.title = title;
    }

    public static JobBuilder aJob(){ 
        return new JobBuilder(aJobId().build(), aUser().build(), aTitle().build());
    }
    
    public Job build(){return Job.createJob(jobId, user, title);}

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
    
    public static class TitleBuilder {
        private final String id;

        public TitleBuilder(String id) {
            this.id = id;
        }

        public static TitleBuilder aTitle() {
            return new TitleBuilder("Programmer");
        }

        public Title build() {
            return Title.create(id);
        }
    }
}
