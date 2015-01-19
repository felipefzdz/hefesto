package org.olid16.infrastructure.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.domain.values.JobType;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.entities.Job;
import org.olid16.infrastructure.rest.resources.JobResource;

import javax.ws.rs.WebApplicationException;

import static builders.JobBuilder.aJob;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class JobResourceShould {

    @Mock CreateJob createJob;
    @Mock GetJobs getJobs;
    @Mock AddJobseekerToJob addJobSeekerToJob;
    @Mock JobAdapter jobAdapter;

    @Test public void
    return_job_id_when_create_job(){
        given(createJob.with(any())).willReturn(aJob().build());
        given(jobAdapter.fromDomain(aJob().build())).willReturn(emptyRestJob());
        Job job = (Job) new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).create(emptyRestJob()).getEntity();
        assertThat(job).isNotNull();
    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid(){
        given(createJob.with(any())).willThrow(ValidationException.class);
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).create(emptyRestJob()));
    }

    @Test public void
    call_get_jobs_by_employer(){
        given(getJobs.byEmployer("")).willReturn(asList(aJob().build()));
        new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getByEmployer("");
        verify(getJobs).byEmployer(anyString());
    }


    @Test public void
    return_404_when_not_jobs_by_employer_found(){
        given(getJobs.byEmployer("")).willReturn(newArrayList());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getByEmployer(""));

    }

    @Test public void
    call_get_jobs_by_jobseeker(){
        given(getJobs.byJobseeker("")).willReturn(asList(aJob().build()));
        new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getByJobseeker("");
        verify(getJobs).byJobseeker(anyString());
    }

    @Test public void
    return_404_when_not_jobs_by_jobseeker_found(){
        given(getJobs.byJobseeker("")).willReturn(newArrayList());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getByJobseeker(""));

    }

    @Test public void
    call_get_jobs_all(){
        given(getJobs.all()).willReturn(asList(aJob().build()));
        new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getAll();
        verify(getJobs).all();
    }

    @Test public void
    return_404_when_not_jobs_found(){
        given(getJobs.all()).willReturn(newArrayList());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).getAll());
    }

    @Test public void
    call_add_jobseeker_to_job() {
        new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).addJobseekerToJob(emptyRestJob(), "");
        verify(addJobSeekerToJob).with(anyString(), anyString());

    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid_with_add_jobseeker_to_job_action(){
        doThrow(AuthorizationException.class).when(addJobSeekerToJob).with(anyString(), anyString());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob, jobAdapter).addJobseekerToJob(emptyRestJob(), ""));

    }

    public Job emptyRestJob() {
        return new Job("", "", "", "", "", null);
    }

}
