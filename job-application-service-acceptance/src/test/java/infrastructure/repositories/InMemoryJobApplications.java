package infrastructure.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.UserId;

import java.util.UUID;

public class InMemoryJobApplications implements JobApplications{

    private Multimap<String, JobApplication> jobApplications = ArrayListMultimap.create();
    
    @Override
    public JobApplication add(JobApplication jobApplication) {
        JobApplication createdJobApplication = JobApplication.create(
                JobApplicationId.create(UUID.randomUUID().toString()),
                JobId.create(jobApplication.jobId()),
                UserId.create(jobApplication.jobseekerId())
        );
        jobApplications.put(createdJobApplication.id(), createdJobApplication);
        return createdJobApplication;
    }
}
