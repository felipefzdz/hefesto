package org.olid16.actions;

import builders.JobBuilder;
import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static utils.Assert.assertThrows;


@RunWith(MockitoJUnitRunner.class)
public class GetJobsShould {

    @Mock UserFactory userFactory;
    @Mock Jobs jobs;

    @Test
    public void
    return_jobs_when_user_is_employer(){
        given(userFactory.create(null)).willReturn(Optional.of(UserBuilder.aUser().w(UserRole.EMPLOYER).build()));
        given(jobs.by(anyString())).willReturn(Arrays.asList(JobBuilder.aJob().build()));
        List<Job> jobsByEmployer = new GetJobs(userFactory, jobs).with(null);
        assertThat(jobsByEmployer).isNotEmpty();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_employer(){
        given(userFactory.create(null)).willReturn(Optional.of(UserBuilder.aUser().build()));
        assertThrows(AuthorizationException.class, () -> new GetJobs(userFactory, jobs).with(null));
    }

    @Test public void
    throw_authorization_exception_when_user_not_exists(){
        given(userFactory.create(null)).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> new GetJobs(userFactory, jobs).with(null));
    }
}
