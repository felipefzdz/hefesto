package org.olid16.infrastructure.repositories;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.olid16.domain.entities.JobApplication;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

public class JobApplicationAdapterShould {

    @Test
    public void
    adapt_to_db_object(){
        DBObject dbObject = new JobApplicationAdapter().toDbObject(aJobApplication().build());
        assertThat(dbObject.get("jobseekerId")).isNotNull();
    }

    @Test public void
    adapt_from_db_object() {
        JobApplication jobApplication = new JobApplicationAdapter().fromDbObject(dbObjectWithAllFields());
        assertThat(jobApplication.id()).is("id");
    }

    private DBObject dbObjectWithAllFields() {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("_id", "id");
        dbObject.append("jobseekerId", "jobseekerId");
        dbObject.append("jobId", "jobId");
        return dbObject;
    }

}