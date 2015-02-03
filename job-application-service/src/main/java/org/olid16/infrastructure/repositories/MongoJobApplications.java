package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.infrastructure.dependency_injection.JobApplicationsDb;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<JobApplication> byJobseekerId(String jobseekerId) {
        DBCursor cursor = jobApplications.find(new BasicDBObject("jobseekerId", jobseekerId));
        return adapt(cursor);
    }

    private List<JobApplication> adapt(DBCursor cursor) {
        List<JobApplication> jobApplications = new ArrayList<>();
        while(cursor.hasNext()){
            jobApplications.add(jobApplicationAdapter.fromDbObject(cursor.next()));
        }
        return jobApplications;
    }
}
