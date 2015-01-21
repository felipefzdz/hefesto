package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.infrastructure.exceptions.DomainException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;
import static org.olid16.utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobApplicationShould {

    @Mock JobApplications jobApplications;
    @Mock JobApplication jobApplication;

    @Test public void
    return_a_job_application_with_id_when_job_application_is_valid() {
        given(jobApplications.add(any())).willReturn(aJobApplication().build());
        JobApplication createdJobApplication = new CreateJobApplication(jobApplications).with(jobApplication);
        assertThat(createdJobApplication.id()).isNotEmpty();
    }

    @Test public void
    throw_domain_exception_when_job_application_is_not_valid(){
        doThrow(DomainException.class).when(jobApplication).validate();
        assertThrows(DomainException.class, () -> new CreateJobApplication(jobApplications).with(jobApplication));
    }
}