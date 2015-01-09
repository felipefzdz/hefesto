package org.olid16.infrastructure.rest;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;
import spark.Request;
import spark.RequestResponseFactory;
import spark.Response;

import java.util.Optional;

import static builders.JobBuilder.JobIdBuilder.aJobId;
import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerShould {

    @Mock CreateJob createJob;
    @Mock Request request;
    @Mock GetJobs getJobs;
    @Mock AddJobseekerToJob addJobSeekerToJob;

    @Test public void
    return_job_id_when_create_job(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willReturn(aJob().build());
        String id = new JobController(createJob, getJobs, addJobSeekerToJob).create(request, null);
        assertThat(id).isEqualTo(aJobId().build().id());
    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willThrow(ValidationException.class);
        Response response = spy(dummyResponse());
        new JobController(createJob, getJobs, addJobSeekerToJob).create(request, response);
        verify(response).status(HttpStatus.BAD_REQUEST_400);
    }

    @Test public void
    return_authorization_message_error_when_invalid_role(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willThrow(new AuthorizationException("Only employers can create jobs"));
        String message = new JobController(createJob, getJobs, addJobSeekerToJob).create(request, dummyResponse());
        assertThat(message).isEqualTo("Only employers can create jobs");
    }
    
    @Test public void
    call_get_jobs_by_employer(){
        given(request.params("employerId")).willReturn("1234");
        given(getJobs.byEmployer("1234")).willReturn(Optional.empty());
        new JobController(createJob, getJobs, addJobSeekerToJob).getByEmployer(request, dummyResponse());
        verify(getJobs).byEmployer(anyString());
    }

    @Test public void
    return_404_when_not_jobs_by_employer_found(){
        given(request.params("employerId")).willReturn("1234");
        given(getJobs.byEmployer("1234")).willReturn(Optional.empty());
        Response response = spy(dummyResponse());
        new JobController(createJob, getJobs, addJobSeekerToJob).getByEmployer(request, response);
        verify(response).status(HttpStatus.NOT_FOUND_404);
    }

    @Test public void
    call_get_jobs_all(){
        given(getJobs.all()).willReturn(Optional.of(""));
        new JobController(createJob, getJobs, addJobSeekerToJob).getAll(dummyResponse());
        verify(getJobs).all();
    }

    @Test public void
    return_404_when_not_jobs_found(){
        given(getJobs.all()).willReturn(Optional.empty());
        Response response = spy(dummyResponse());
        new JobController(createJob, getJobs, addJobSeekerToJob).getAll(response);
        verify(response).status(HttpStatus.NOT_FOUND_404);
    }

    @Test public void
    call_add_jobseeker_to_job() {
        given(request.body()).willReturn("{}");
        given(request.params("jobId")).willReturn("1234");
        new JobController(createJob, getJobs, addJobSeekerToJob).addJobseekerToJob(request, dummyResponse());
        verify(addJobSeekerToJob).with(any(JsonEntity.class), anyString());

    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid_with_add_jobseeker_to_job_action(){
        given(request.body()).willReturn("{}");
        given(request.params("jobId")).willReturn("1234");
        doThrow(AuthorizationException.class).when(addJobSeekerToJob).with(any(JsonEntity.class), anyString());
        Response response = spy(dummyResponse());
        new JobController(createJob, getJobs, addJobSeekerToJob).addJobseekerToJob(request, response);
        verify(response).status(HttpStatus.BAD_REQUEST_400);
    }

    private Response dummyResponse() {
        return RequestResponseFactory.create(new org.eclipse.jetty.server.Response(null, null));
    }
        
}
