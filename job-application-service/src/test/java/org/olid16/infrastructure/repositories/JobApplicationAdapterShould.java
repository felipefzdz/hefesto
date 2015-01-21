package org.olid16.infrastructure.repositories;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.factories.JobApplicationFactory;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationAdapterShould {

    @Mock JobApplicationFactory jobApplicationFactory;

    @Test
    public void
    adapt_to_db_object(){
        DBObject dbObject = new JobApplicationAdapter(jobApplicationFactory).toDbObject(aJobApplication().build());
        assertThat(dbObject.get("jobseekerId")).isNotNull();
    }

    @Test public void
    adapt_from_db_object() {
        given(jobApplicationFactory.create(any(), any(), any(), any())).willReturn(aJobApplication().build());
        JobApplication jobApplication = new JobApplicationAdapter(jobApplicationFactory).fromDbObject(dbObjectWithAllFields());
        assertThat(jobApplication.id()).is("1234");
    }

    private DBObject dbObjectWithAllFields() {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.append("_id", "id");
        dbObject.append("jobseekerId", "jobseekerId");
        dbObject.append("jobId", "jobId");
        dbObject.append("resumeId", "resumeId");
        return dbObject;
    }

}