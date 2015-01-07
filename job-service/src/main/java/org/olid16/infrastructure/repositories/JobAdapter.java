package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JobAdapter {
    private JobFactory jobfactory;

    @Inject
    public JobAdapter(JobFactory jobfactory) {
        this.jobfactory = jobfactory;
    }

    public DBObject toDBObject(Job job) {
        return new BasicDBObject("_id", job.id())
                .append("employer_id", job.employerId())
                .append("title", job.title());
    }

    public List<Job> from(Iterator<DBObject> it) {
        List<Job> jobs = new ArrayList<>();
        while(it.hasNext()){
            jobs.add(createJob(it.next()));
        }
        return jobs;
    }

    private Job createJob(DBObject dbObject) {
        return jobfactory.create(
                dbObject.get("_id").toString(), 
                dbObject.get("employer_id").toString(), 
                dbObject.get("title").toString());
    }
}
