package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.Iterator;
import java.util.List;

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
    public List<Job> by(String employerId) {
        Iterator<DBObject> dbObjectIterator = jobs.find(new BasicDBObject("employer_id", employerId)).iterator();
        return jobAdapter.from(dbObjectIterator);
    }
}
