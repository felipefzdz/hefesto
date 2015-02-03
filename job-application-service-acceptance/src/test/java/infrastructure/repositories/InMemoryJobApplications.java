package infrastructure.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.factories.JobApplicationFactory;
import org.olid16.domain.values.*;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class InMemoryJobApplications implements JobApplications{

    private Multimap<String, JobApplication> jobApplications = ArrayListMultimap.create();
    private final JobApplicationFactory jobApplicationFactory;

    @Inject
    public InMemoryJobApplications(JobApplicationFactory jobApplicationFactory) {
        this.jobApplicationFactory = jobApplicationFactory;
    }

    @Override
    public JobApplication add(JobApplication jobApplication) {
        JobApplication createdJobApplication = jobApplicationFactory.create(
                JobApplicationId.create(UUID.randomUUID().toString()),
                JobId.create(jobApplication.jobId()),
                UserId.create(jobApplication.jobseekerId()),
                ResumeId.create(jobApplication.resumeId())
        );
        jobApplications.put(createdJobApplication.id(), createdJobApplication);
        return createdJobApplication;
    }

    @Override
    public List<JobApplication> byJobseekerId(String jobseekerId) {
        return jobApplications.values().stream()
                .filter(jobApplication -> jobApplication.jobseekerId().equals(jobseekerId))
                .collect(toList());
    }
}
