package org.olid16.infrastructure.repositories;

import builders.JobBuilder;
import builders.UserBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.List;
import java.util.Optional;

import static builders.JobBuilder.JobIdBuilder.*;
import static builders.UserBuilder.UserIdBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MongoJobsShould {

    @Mock DBCollection dbCollection;
    @Mock JobAdapter jobAdapter;
    @Mock DBCursor cursor;

    @Test public void
    return_different_ids(){
        JobId jobId1 = new MongoJobs(null, null).nextId();
        JobId jobId2 = new MongoJobs(null, null).nextId();
        assertThat(jobId1).isNotEqualTo(jobId2);
    }
    
    @Test public void
    insert_a_job(){
        new MongoJobs(dbCollection, jobAdapter).add(null);
        verify(dbCollection).insert((DBObject)null);
    }
    
    @Test public void
    return_several_jobs_by_employer_id() {
        given(cursor.hasNext()).willReturn(true, true, false);
        given(dbCollection.find(any())).willReturn(cursor);
        given(jobAdapter.fromDBObject(null)).willReturn(JobBuilder.aJob().build());
        List<Job> jobs = new MongoJobs(dbCollection, jobAdapter).byEmployerId("");
        assertThat(jobs.size()).is(2);
    }
    
    @Test public void
    return_no_jobs_when_cursor_is_empty(){
        given(cursor.hasNext()).willReturn(false);
        given(dbCollection.find(any())).willReturn(cursor);
        List<Job> jobs = new MongoJobs(dbCollection, jobAdapter).byEmployerId("");
        assertThat(jobs).isEmpty();
    }

    @Test public void
    return_several_all_jobs() {
        given(cursor.hasNext()).willReturn(true, true, false);
        given(dbCollection.find()).willReturn(cursor);
        given(jobAdapter.fromDBObject(null)).willReturn(JobBuilder.aJob().build());
        List<Job> jobs = new MongoJobs(dbCollection, jobAdapter).all();
        assertThat(jobs.size()).is(2);
    }

    @Test public void
    return_several_jobs_by_jobseeker_id() {
        given(cursor.hasNext()).willReturn(true, true, false);
        given(dbCollection.find(any())).willReturn(cursor);
        given(jobAdapter.fromDBObject(null)).willReturn(JobBuilder.aJob().build());
        List<Job> jobs = new MongoJobs(dbCollection, jobAdapter).byJobseekerId("");
        assertThat(jobs.size()).is(2);
    }
    
    @Test public void
    add_a_jobseeker(){
        new MongoJobs(dbCollection, null).addJobseeker(aJobId().build(), aUserId().build());
        verify(dbCollection).update(any(), any());
    }

    @Test public void
    update_employer_name(){
        new MongoJobs(dbCollection, null).updateEmployerName("", "");
        verify(dbCollection).updateMulti(any(), any());
    }

    @Test public void
    return_a_job_by_id_when_found() {
        given(cursor.one()).willReturn(new BasicDBObject());
        given(dbCollection.find(any())).willReturn(cursor);
        given(jobAdapter.fromDBObject(any())).willReturn(JobBuilder.aJob().build());
        Optional<Job> job = new MongoJobs(dbCollection, jobAdapter).byId("");
        assertThat(job.isPresent()).isTrue();
    }

    @Test public void
    return_empty_job_by_id_when_not_found() {
        given(cursor.one()).willReturn(null);
        given(dbCollection.find(any())).willReturn(cursor);
        Optional<Job> job = new MongoJobs(dbCollection, jobAdapter).byId("");
        assertThat(job.isPresent()).isFalse();
    }
    
}