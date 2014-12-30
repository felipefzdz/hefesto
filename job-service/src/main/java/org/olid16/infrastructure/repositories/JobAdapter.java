package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.Job;

public class JobAdapter {
    public DBObject toDBObject(Job job) {
        return new BasicDBObject("_id", job.id())
                .append("employer_id", job.employerId())
                .append("title", job.title());
    }
}
