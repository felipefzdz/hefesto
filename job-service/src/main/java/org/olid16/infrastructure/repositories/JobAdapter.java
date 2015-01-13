package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.Job;

public class JobAdapter {
    
    public DBObject toDBObject(Job job) {
        BasicDBObject employer = new BasicDBObject("id", job.employerId())
                .append("name", job.employerName());
        return new BasicDBObject("_id", job.id())
                .append("employer", employer)
                .append("title", job.title());
    }

}
