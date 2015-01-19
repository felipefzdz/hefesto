package org.olid16.infrastructure.rest;

import org.junit.Test;
import org.olid16.domain.values.JobId;
import org.olid16.infrastructure.rest.entities.Job;

import java.util.List;

import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

public class JobAdapterShould {
    
    @Test public void
    adapt_from_domain(){
        Job job = new JobAdapter().fromDomain(aJob().build());
        assertThat(job.getId()).isEqualTo("1234");
    }

    @Test public void
    adapt_list_from_domain(){
        List<Job> jobs = new JobAdapter().fromDomain(asList(aJob().build(), aJob().w(JobId.create("2345")).build()));
        assertThat(jobs.size()).is(2);
    }

}