package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoJobs implements Jobs {
    private final DBCollection jobs;
    private final JobAdapter jobAdapter;

    @Inject
    public MongoJobs(DBCollection jobs, JobAdapter jobAdapter) {
        this.jobAdapter = jobAdapter;
        this.jobs = jobs;
    }
    @Override
    public JobId nextId() {
        return JobId.create(ObjectId.get().toString());
    }

    @Override
    public void add(Job job) {
        jobs.insert(jobAdapter.toDBObject(job));
    }

    @Override
    public List<Job> byEmployerId(String employerId) {
        DBCursor cursor = jobs.find(new BasicDBObject("employer.id", employerId));
        return adapt(cursor);
    }

    @Override
    public List<Job> all() {
        DBCursor cursor = jobs.find();
        return adapt(cursor);
    }

    @Override
    public List<Job> byJobseekerId(String jobseekerId) {
        DBCursor cursor = jobs.find(new BasicDBObject("jobseekers", jobseekerId));
        return adapt(cursor);
    }

    @Override
    public void addJobseeker(JobId jobId, UserId jobseekerId) {
        BasicDBObject query = new BasicDBObject().append("_id", jobId.id());
        BasicDBObject modification = new BasicDBObject().append("$addToSet", new BasicDBObject().append("jobseekers", jobseekerId.id()));
        jobs.update(query, modification);
    }

    @Override
    public void updateEmployerName(String employerId, String name) {
        BasicDBObject query = new BasicDBObject().append("employer.id", employerId);
        BasicDBObject modification = new BasicDBObject();
        modification.append("$set", new BasicDBObject().append("employer.name", name));
        jobs.updateMulti(query, modification);
    }

    @Override
    public Optional<Job> byId(String id) {
        DBObject dbObject = jobs.find(new BasicDBObject("_id", id)).one();
        return dbObject == null ? Optional.empty() : Optional.of(jobAdapter.fromDBObject(dbObject));
    }

    private List<Job> adapt(DBCursor cursor) {
        List<Job> jobs = new ArrayList<>();
        while(cursor.hasNext()){
            jobs.add(jobAdapter.fromDBObject(cursor.next()));
        }
        return jobs;
    }
}
