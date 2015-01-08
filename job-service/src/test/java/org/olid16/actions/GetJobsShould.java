package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class GetJobsShould {

    @Mock Jobs jobs;

    @Test public void
    return_jobs_by_employer(){
        given(jobs.byEmployerId(anyString())).willReturn(Optional.of(""));
        Optional<String> jobsByEmployer = new GetJobs(jobs).byEmployer("1234");
        assertThat(jobsByEmployer.isPresent()).isTrue();
    }

    @Test public void
    return_all_jobs_(){
        given(jobs.all()).willReturn(Optional.of(""));
        Optional<String> allJobs = new GetJobs(jobs).all();
        assertThat(allJobs.isPresent()).isTrue();
    }

}
