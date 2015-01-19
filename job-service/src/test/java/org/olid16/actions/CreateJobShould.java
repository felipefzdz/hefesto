package org.olid16.actions;

import builders.JobBuilder;
import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.entities.Jobseekers;
import org.olid16.domain.factories.JobFactory;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Arrays;
import java.util.Optional;

import static builders.JobBuilder.JobIdBuilder.*;
import static builders.JobBuilder.aJob;
import static builders.UserBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobShould {

    @Mock
    UserClient userClient;
    @Mock JobFactory jobFactory;
    @Mock Jobs jobs;
    @Mock UserIdFactory userIdFactory;

    @Test public void
    return_job_when_user_is_employer(){
        given(userClient.create(any(UserId.class))).willReturn(Optional.of(aUser().w(UserRole.EMPLOYER).build()));
        given(jobFactory.create(any(User.class), anyString(), any(JobId.class), anyString())).willReturn(aJob().build());
        Job job = new CreateJob(userClient, jobFactory, jobs, userIdFactory).with(jobFromClient());
        assertThat(job).isNotNull();
    }

    private Job jobFromClient() {
        return new Job(aJobId().build(), aUser().build(), Title.create(""), Jobseekers.create(), JobType.ATS);
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_employer(){
        given(userClient.create(null)).willReturn(Optional.of(aUser().build()));
        assertThrows(AuthorizationException.class, () -> new CreateJob(userClient, jobFactory, jobs, userIdFactory).with(jobFromClient()));
    }

    @Test public void
    throw_authorization_exception_when_user_not_exists(){
        given(userClient.create(null)).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> new CreateJob(userClient, jobFactory, jobs, userIdFactory).with(jobFromClient()));
    }
}
