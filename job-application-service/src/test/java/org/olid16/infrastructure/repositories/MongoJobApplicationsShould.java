package org.olid16.infrastructure.repositories;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.JobApplication;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

@RunWith(MockitoJUnitRunner.class)
public class MongoJobApplicationsShould {

    @Mock DBCollection dbCollection;
    @Mock JobApplicationAdapter jobApplicationAdapter;
    @Mock
    DBCursor cursor;

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

    @Test public void
    return_several_job_applications_by_jobseeker_id() {
        given(cursor.hasNext()).willReturn(true, true, false);
        given(dbCollection.find(any())).willReturn(cursor);
        given(jobApplicationAdapter.fromDbObject(null)).willReturn(aJobApplication().build());
        List<JobApplication> jobApplications = new MongoJobApplications(jobApplicationAdapter, dbCollection).byJobseekerId("");
        assertThat(jobApplications.size()).is(2);
    }

    @Test public void
    return_no_job_applications_when_cursor_is_empty(){
        given(cursor.hasNext()).willReturn(false);
        given(dbCollection.find(any())).willReturn(cursor);
        List<JobApplication> jobApplications = new MongoJobApplications(jobApplicationAdapter, dbCollection).byJobseekerId("");
        assertThat(jobApplications).isEmpty();
    }
}