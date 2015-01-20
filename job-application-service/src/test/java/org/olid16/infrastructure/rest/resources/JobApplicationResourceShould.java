package org.olid16.infrastructure.rest.resources;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olid16.actions.CreateJobApplication;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.adapters.JobApplicationAdapter;
import org.olid16.infrastructure.rest.entities.JobApplication;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.olid16.utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationResourceShould {

    @Mock CreateJobApplication createJobApplication;
    @Mock JobApplicationAdapter jobApplicationAdapter;

    @Test public void
    return_job_application_id_when_create_job_application() {
        given(jobApplicationAdapter.fromDomain(null)).willReturn(new JobApplication("id", "", ""));
        JobApplication jobApplication = new JobApplication(null, null, null);
        JobApplication createdJobApplication = (JobApplication) new JobApplicationResource(createJobApplication, jobApplicationAdapter).create(jobApplication).getEntity();
        assertThat(createdJobApplication.getJobId()).isNotNull();
    }

    @Test public void
    return_bad_request_when_there_is_a_domain_exception() {
        given(createJobApplication.with(null)).willThrow(ValidationException.class);
        assertThrows(ValidationException.class, () -> new JobApplicationResource(createJobApplication, jobApplicationAdapter).create(new JobApplication(null, null, null)));
    }
}