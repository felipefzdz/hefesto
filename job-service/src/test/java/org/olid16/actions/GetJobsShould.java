package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;

import java.util.List;

import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class GetJobsShould {

    @Mock Jobs jobs;

    @Test public void
    return_jobs_by_employer(){
        given(jobs.byEmployerId(anyString())).willReturn(asList(aJob().build()));
        List<Job> jobsByEmployer = new GetJobs(jobs).byEmployer("1234");
        assertThat(jobsByEmployer.isEmpty()).isFalse();
    }

    @Test public void
    return_all_jobs(){
        given(jobs.all()).willReturn(asList(aJob().build()));
        List<Job> allJobs = new GetJobs(jobs).all();
        assertThat(allJobs.isEmpty()).isFalse();
    }
    
    @Test public void 
    return_jobs_by_jobseeker(){
        given(jobs.byJobseekerId(anyString())).willReturn(asList(aJob().build()));
        List<Job> jobsByJobseeker = new GetJobs(jobs).byJobseeker("");
        assertThat(jobsByJobseeker.isEmpty()).isFalse();
        
    }

}
