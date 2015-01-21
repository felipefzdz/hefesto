package org.olid16.infrastructure.rest.adapters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.factories.JobApplicationFactory;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationAdapterShould {

    @Mock JobApplicationFactory jobApplicationFactory;

    @Test
    public void
    adapt_to_domain(){
        given(jobApplicationFactory.create(any(), any(), any(), any())).willReturn(aJobApplication().build());
        JobApplication jobApplication = new JobApplicationAdapter(jobApplicationFactory).toDomain(new org.olid16.infrastructure.rest.entities.JobApplication("", "", "", ""));
        assertThat(jobApplication.jobId()).is("1234");
    }

    @Test public void
    adapt_from_domain() {
        org.olid16.infrastructure.rest.entities.JobApplication jobApplication = new JobApplicationAdapter(jobApplicationFactory).fromDomain(aJobApplication().build());
        assertThat(jobApplication.getId()).is(aJobApplication().build().id());
    }

}