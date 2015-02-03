package org.olid16.actions;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;

@RunWith(MockitoJUnitRunner.class)
public class GetJobApplicationsShould {

    @Mock JobApplications jobApplications;

    @Test public void
    return_jobs_by_jobseeker(){
        given(jobApplications.byJobseekerId(anyString())).willReturn(asList(aJobApplication().build()));
        List<JobApplication> jobApplicationsByJobseeker = new GetJobApplications(jobApplications).byJobseeker("");
        assertThat(jobApplicationsByJobseeker.isEmpty()).isFalse();

    }

}