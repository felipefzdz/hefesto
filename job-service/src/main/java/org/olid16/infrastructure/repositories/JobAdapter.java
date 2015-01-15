package org.olid16.infrastructure.repositories;

import com.google.common.base.Splitter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.Job;
import org.olid16.domain.entities.Jobseekers;
import org.olid16.domain.values.*;

public class JobAdapter {
    
    public DBObject toDBObject(Job job) {
        BasicDBObject employer = new BasicDBObject("id", job.employerId())
                .append("name", job.employerName());
        return new BasicDBObject("_id", job.id())
                .append("employer", employer)
                .append("title", job.title());
    }

    public Job fromDBObject(DBObject dbObject) {
        return Job.createJob(
                jobId(dbObject),
                user(dbObject),
                title(dbObject),
                jobseekers(dbObject));
    }

    private JobId jobId(DBObject dbObject) {
        return JobId.create(extractField(dbObject, "_id"));
    }

    private Title title(DBObject dbObject) {
        return Title.create(extractField(dbObject, "title"));
    }

    private User user(DBObject dbObject) {
        BasicDBObject employer = (BasicDBObject) dbObject.get("employer");
        return User.create(
                Person.create(extractField(employer, "name")),
                UserRole.UNKNOWN,
                UserId.create(extractField(employer, "id")));
    }

    private Jobseekers jobseekers(DBObject dbObject) {
        Jobseekers jobseekers = Jobseekers.create();
        if(dbObject.get("jobseekers") == null) {
            return jobseekers;
        }
        Splitter.on(",").splitToList(extractField(dbObject, "jobseekers")).stream()
        .forEach(jobseekerId -> jobseekers.add(UserId.create(jobseekerId)));
        return jobseekers;
    }

    private String extractField(DBObject dbObject, String field) {
        return dbObject.get(field).toString();
    }
}
