package org.olid16.actions;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.UserRole;

import java.util.Optional;

import static builders.UserBuilder.UserIdBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;


@RunWith(MockitoJUnitRunner.class)
public class GetJobsShould {

    @Mock UserFactory userFactory;
    @Mock Jobs jobs;

    @Test
    public void
    return_jobs_when_user_is_employer(){
        given(userFactory.create(aUserId().build())).willReturn(Optional.of(UserBuilder.aUser().w(UserRole.EMPLOYER).build()));
        given(jobs.byEmployerId(anyString())).willReturn(Optional.of(""));
        Optional<String> jobsByEmployer = new GetJobs(userFactory, jobs).byEmployerId("1234");
        assertThat(jobsByEmployer.isPresent()).isTrue();
    }

}
