package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.factories.JobApplicationFactory;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public class JobApplicationAdapter {
    private final JobApplicationFactory jobApplicationFactory;

    @Inject
    public JobApplicationAdapter(JobApplicationFactory jobApplicationFactory) {
        this.jobApplicationFactory = jobApplicationFactory;
    }

    public DBObject toDbObject(JobApplication jobApplication) {
        BasicDBObject dbObject = new BasicDBObject("jobseekerId", jobApplication.jobseekerId());
        dbObject.append("jobId", jobApplication.jobId());
        dbObject.append("resumeId", jobApplication.resumeId());
        return dbObject;
    }

    public JobApplication fromDbObject(DBObject dbJobApplication) {
        return jobApplicationFactory.create(
                        JobApplicationId.create(extractField(dbJobApplication, "_id")),
                        JobId.create(extractField(dbJobApplication, "jobId")),
                        UserId.create(extractField(dbJobApplication, "jobseekerId")),
                ResumeId.create(extractOptionalField(dbJobApplication, "resumeId")));
    }

    private String extractField(DBObject dbObject, String field) {
        return dbObject.get(field).toString();
    }

    private String extractOptionalField(DBObject dbObject, String field) {
        Object possibleField = dbObject.get(field);
        return possibleField == null ? 
                "" :
                possibleField.toString();
    }


}
