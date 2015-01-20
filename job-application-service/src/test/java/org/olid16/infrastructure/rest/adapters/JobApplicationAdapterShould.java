package org.olid16.infrastructure.rest.adapters;

import org.junit.Test;
import org.olid16.domain.entities.JobApplication;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

public class JobApplicationAdapterShould {

    @Test
    public void
    adapt_to_domain(){
        JobApplication jobApplication = new JobApplicationAdapter().toDomain(new org.olid16.infrastructure.rest.entities.JobApplication("jobId", "jobseekerId", "id"));
        assertThat(jobApplication.jobId()).is("jobId");
    }

    @Test public void
    adapt_from_domain() {
        org.olid16.infrastructure.rest.entities.JobApplication jobApplication = new JobApplicationAdapter().fromDomain(aJobApplication().build());
        assertThat(jobApplication.getId()).is(aJobApplication().build().id());
    }

}