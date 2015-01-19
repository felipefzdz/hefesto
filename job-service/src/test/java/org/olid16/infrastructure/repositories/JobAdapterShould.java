package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.olid16.domain.entities.Job;

import static builders.JobBuilder.aJob;
import static builders.UserBuilder.UserIdBuilder.aUserId;
import static com.google.common.truth.Truth.assertThat;


public class JobAdapterShould {
    
    @Test public void
    adapt_to_db_object(){
        Job job = aJob().build();
        DBObject dbObject = new JobAdapter().toDBObject(job);
        assertThat(dbObject.get("_id").toString()).isEqualTo(job.id());
    }

    @Test public void
    adapt_from_db_object(){
        Job job = new JobAdapter().fromDBObject(dbObjectWithAllFields());
        assertThat(job.interestedJobseekers().contains(aUserId().build())).isTrue();
    }

    @Test public void
    adapt_from_db_object_without_jobseekers(){
        BasicDBObject dbObject = dbObjectWithAllFields();
        dbObject.remove("jobseekers");
        Job job = new JobAdapter().fromDBObject(dbObject);
        assertThat(job.interestedJobseekers().contains(aUserId().build())).isFalse();
    }

    public BasicDBObject dbObjectWithAllFields() {
        BasicDBObject dbObject = new BasicDBObject("_id", "1234");
        dbObject.append("title", "title");
        dbObject.append("employer", employer());
        dbObject.append("jobseekers", "1234, 5678");
        dbObject.append("type", "ats");
        return dbObject;
    }

    private BasicDBObject employer() {
        BasicDBObject dbObject = new BasicDBObject("id", "id");
        dbObject.append("name", "name");
        return dbObject;
    }


}