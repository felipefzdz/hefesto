package org.olid16.infrastructure.repositories;

import com.eclipsesource.json.JsonArray;
import com.google.inject.Inject;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

import java.util.Iterator;
import java.util.Optional;

public class MongoJobs implements Jobs {
    private final DBCollection jobs;
    private final JobAdapter jobAdapter;

    @Inject
    public MongoJobs(DB database, JobAdapter jobAdapter) {
        this.jobAdapter = jobAdapter;
        this.jobs = database.getCollection("jobs");
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
    public Optional<String> byEmployerId(String employerId) {
        DBCursor cursor = jobs.find(new BasicDBObject("employer.id", employerId));
        return adapt(cursor);
    }

    @Override
    public Optional<String> all() {
        DBCursor cursor = jobs.find();
        return adapt(cursor);
    }

    @Override
    public void addJobseeker(JobId jobId, UserId jobseekerId) {
        BasicDBObject query = new BasicDBObject().append("_id", jobId.id());
        BasicDBObject modification = new BasicDBObject().append("$addToSet", new BasicDBObject().append("jobseekers", jobseekerId.id()));
        jobs.update(query, modification);
    }

    @Override
    public Optional<String> byJobseekerId(String jobseekerId) {
        DBCursor cursor = jobs.find(new BasicDBObject("jobseekers", jobseekerId));
        return adapt(cursor);
    }

    private Optional<String> adapt(DBCursor cursor) {
        Iterator<DBObject> it = cursor.iterator();
        if (!it.hasNext()){
            return Optional.empty();
        }
        JsonArray jsonArray = new JsonArray();
        while(it.hasNext()){
            jsonArray.add(it.next().toString());
        }
        return Optional.of(jsonArray.toString());
    }
}
