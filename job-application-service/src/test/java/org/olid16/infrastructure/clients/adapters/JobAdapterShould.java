package org.olid16.infrastructure.clients.adapters;


import org.junit.Test;
import org.olid16.domain.values.Job;

import static com.google.common.truth.Truth.assertThat;

public class JobAdapterShould {

    @Test public void
    adapt_from_client(){
        Job job = new JobAdapter().fromClient(new org.olid16.infrastructure.clients.entities.Job("id", "title", "unknown"));
        assertThat(job.jobId()).isNotNull();
    }
}