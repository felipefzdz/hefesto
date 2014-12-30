package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

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
}
