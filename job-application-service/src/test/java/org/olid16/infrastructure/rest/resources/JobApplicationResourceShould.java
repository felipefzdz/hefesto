package org.olid16.infrastructure.rest.resources;

import org.eclipse.jetty.http.HttpStatus;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olid16.actions.CreateJobApplication;
import org.olid16.actions.GetJobApplications;
import org.olid16.builders.JobApplicationBuilder;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.adapters.JobApplicationAdapter;
import org.olid16.infrastructure.rest.entities.JobApplication;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.truth.Truth.assertThat;
import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.JobApplicationBuilder.*;
import static org.olid16.utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationResourceShould {

    @Mock CreateJobApplication createJobApplication;
    @Mock JobApplicationAdapter jobApplicationAdapter;
    @Mock GetJobApplications getJobApplications;

    @Test public void
    return_job_application_id_when_create_job_application() {
        given(jobApplicationAdapter.fromDomain(any(org.olid16.domain.entities.JobApplication.class))).willReturn(new JobApplication("id", "", "", ""));
        JobApplication jobApplication = new JobApplication(null, null, null, null);
        JobApplication createdJobApplication = (JobApplication) new JobApplicationResource(createJobApplication, jobApplicationAdapter, getJobApplications).create(jobApplication).getEntity();
        assertThat(createdJobApplication.getJobId()).isNotNull();
    }

    @Test public void
    return_bad_request_when_there_is_a_domain_exception() {
        given(createJobApplication.with(null)).willThrow(ValidationException.class);
        assertThrows(WebApplicationException.class, () -> new JobApplicationResource(createJobApplication, jobApplicationAdapter, getJobApplications).create(new JobApplication(null, null, null, null)));
    }

    @Test public void
    return_200_response_when_job_applications_by_jobseeker_found(){
        given(getJobApplications.byJobseeker("")).willReturn(newArrayList(aJobApplication().build()));
        Response response = new JobApplicationResource(createJobApplication, jobApplicationAdapter, getJobApplications).getByJobseeker("");
        assertThat(response.getStatus()).is(OK_200);
    }
    
    @Test public void
    return_404_when_not_job_applications_by_jobseeker_found(){
        given(getJobApplications.byJobseeker("")).willReturn(newArrayList());
        assertThrows(WebApplicationException.class, () -> new JobApplicationResource(createJobApplication, jobApplicationAdapter, getJobApplications).getByJobseeker(""));
    }
}