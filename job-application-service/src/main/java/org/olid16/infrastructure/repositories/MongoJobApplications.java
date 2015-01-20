package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.infrastructure.dependency_injection.JobApplicationsDb;

public class MongoJobApplications implements JobApplications {
    private final JobApplicationAdapter jobApplicationAdapter;
    private final DBCollection jobApplications;

    @Inject
    public MongoJobApplications(JobApplicationAdapter jobApplicationAdapter, 
                                @JobApplicationsDb DBCollection jobApplications) {
        this.jobApplicationAdapter = jobApplicationAdapter;
        this.jobApplications = jobApplications;
    }

    @Override
    public JobApplication add(JobApplication jobApplication) {
        DBObject dbJobApplication = jobApplicationAdapter.toDbObject(jobApplication);
        jobApplications.insert(dbJobApplication);
        return jobApplicationAdapter.fromDbObject(dbJobApplication);
    }
}
