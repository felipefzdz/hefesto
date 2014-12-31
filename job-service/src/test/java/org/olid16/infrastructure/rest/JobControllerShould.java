package org.olid16.infrastructure.rest;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateJob;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;
import spark.Request;
import spark.RequestResponseFactory;
import spark.Response;

import static builders.JobBuilder.JobIdBuilder.aJobId;
import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JobControllerShould {

    @Mock CreateJob createJob;
    @Mock Request request;

    @Test public void
    call_create_job(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willReturn(aJob().build());
        new JobController(createJob).create(request, null);
        verify(createJob).with(any(JsonEntity.class));
    }

    @Test public void
    return_job_id_when_create_job(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willReturn(aJob().build());
        String id = new JobController(createJob).create(request, null);
        assertThat(id).isEqualTo(aJobId().build().id());
    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willThrow(ValidationException.class);
        Response response = spy(dummyResponse());
        new JobController(createJob).create(request, response);
        verify(response).status(HttpStatus.BAD_REQUEST_400);
    }

    @Test public void
    return_authorization_message_error_when_invalid_role(){
        given(request.body()).willReturn("{}");
        given(createJob.with(any(JsonEntity.class))).willThrow(new AuthorizationException("Only employers can create jobs"));
        Response response = spy(dummyResponse());
        String message = new JobController(createJob).create(request, response);
        assertThat(message).isEqualTo("Only employers can create jobs");
    }

    private Response dummyResponse() {
        return RequestResponseFactory.create(new org.eclipse.jetty.server.Response(null, null));
    }
        
}
