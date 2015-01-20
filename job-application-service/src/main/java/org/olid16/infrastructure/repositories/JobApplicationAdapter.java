package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.JobApplicationId;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

public class JobApplicationAdapter {
    public DBObject toDbObject(JobApplication jobApplication) {
        BasicDBObject dbObject = new BasicDBObject("jobseekerId", jobApplication.jobseekerId());
        dbObject.append("jobId", jobApplication.jobId());
        return dbObject;
    }

    public JobApplication fromDbObject(DBObject dbJobApplication) {
        return JobApplication.create(
                JobApplicationId.create(extractField(dbJobApplication, "_id")),
                JobId.create(extractField(dbJobApplication, "jobId")),
                UserId.create(extractField(dbJobApplication, "jobseekerId"))
        );
    }

    private String extractField(DBObject dbObject, String field) {
        return dbObject.get(field).toString();
    }
}
