package org.olid16.infrastructure.repositories;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.JobApplication;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

@RunWith(MockitoJUnitRunner.class)
public class MongoJobApplicationsShould {

    @Mock DBCollection dbCollection;
    @Mock JobApplicationAdapter jobApplicationAdapter;

    @Test public void
    add_a_job_application(){
        new MongoJobApplications(jobApplicationAdapter, dbCollection).add(null);
        verify(dbCollection).insert((DBObject) null);
    }

    @Test public void
    return_job_application_id_when_adding_a_job_application() {
        given(jobApplicationAdapter.fromDbObject(null)).willReturn(aJobApplication().build());
        JobApplication jobApplication = new MongoJobApplications(jobApplicationAdapter, dbCollection).add(null);
        assertThat(jobApplication.id()).isNotNull();
    }
}