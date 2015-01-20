package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.olid16.builders.ResumeBuilder;
import org.olid16.domain.entities.Resume;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.builders.ResumeBuilder.*;

public class ResumeAdapterShould {
    
    @Test public void
    adapt_to_db_object(){
        DBObject dbObject = new ResumeAdapter().toDbObject(aResume().build());
        assertThat(dbObject.get("content")).isNotNull();
    }
    
    @Test public void
    adapt_from_db_object() {
        Resume resume = new ResumeAdapter().fromDbObject(dbObjectWithAllFields());
        assertThat(resume.id()).is("id");
    }

    private DBObject dbObjectWithAllFields() {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("_id", "id");
        dbObject.append("jobseekerId", "jobseekerId");
        dbObject.append("content", "content");
        return dbObject;
    }

}