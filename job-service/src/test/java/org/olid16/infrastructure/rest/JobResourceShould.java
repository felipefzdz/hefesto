package org.olid16.infrastructure.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.entities.Job;
import org.olid16.infrastructure.rest.resources.JobResource;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static builders.JobBuilder.JobIdBuilder.aJobId;
import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class JobResourceShould {

    @Mock CreateJob createJob;
    @Mock GetJobs getJobs;
    @Mock AddJobseekerToJob addJobSeekerToJob;

    @Test public void
    return_job_id_when_create_job(){
        given(createJob.with(anyString(), anyString())).willReturn(aJob().build());
        String id = new JobResource(createJob, getJobs, addJobSeekerToJob).create(new Job("", ""));
        assertThat(id).isEqualTo(aJobId().build().id());
    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid(){
        given(createJob.with(anyString(), anyString())).willThrow(ValidationException.class);
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob).create(new Job("", "")));
    }


    @Test public void
    call_get_jobs_by_employer(){
        given(getJobs.byEmployer("")).willReturn(Optional.of(""));
        new JobResource(createJob, getJobs, addJobSeekerToJob).getByEmployer("");
        verify(getJobs).byEmployer(anyString());
    }

    @Test public void
    return_404_when_not_jobs_by_employer_found(){
        given(getJobs.byEmployer("")).willReturn(Optional.empty());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob).getByEmployer(""));

    }

    @Test public void
    call_get_jobs_by_jobseeker(){
        given(getJobs.byJobseeker("")).willReturn(Optional.of(""));
        new JobResource(createJob, getJobs, addJobSeekerToJob).getByJobseeker("");
        verify(getJobs).byJobseeker(anyString());
    }

    @Test public void
    return_404_when_not_jobs_by_jobseeker_found(){
        given(getJobs.byJobseeker("")).willReturn(Optional.empty());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob).getByJobseeker(""));

    }

    @Test public void
    call_get_jobs_all(){
        given(getJobs.all()).willReturn(Optional.of(""));
        new JobResource(createJob, getJobs, addJobSeekerToJob).getAll();
        verify(getJobs).all();
    }

    @Test public void
    return_404_when_not_jobs_found(){
        given(getJobs.all()).willReturn(Optional.empty());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob).getAll());
    }

    @Test public void
    call_add_jobseeker_to_job() {
        new JobResource(createJob, getJobs, addJobSeekerToJob).addJobseekerToJob(new Job("", ""), "");
        verify(addJobSeekerToJob).with(anyString(), anyString());

    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid_with_add_jobseeker_to_job_action(){
        doThrow(AuthorizationException.class).when(addJobSeekerToJob).with(anyString(), anyString());
        assertThrows(WebApplicationException.class, () -> new JobResource(createJob, getJobs, addJobSeekerToJob).addJobseekerToJob(new Job("", ""), ""));

    }

}
