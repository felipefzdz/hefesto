package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Optional;

import static builders.JobBuilder.JobIdBuilder.*;
import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.aUserId;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddJobseekerToJobActionShould {

    @Mock Jobs jobs;
    @Mock UserIdFactory userIdFactory;
    @Mock
    UserClient userClient;

    @Test public void
    call_jobs_add_jobseeker() {
        JobId jobId = aJobId().build();
        UserId jobseekerId = aUserId().build();
        given(userIdFactory.create("")).willReturn(jobseekerId);
        given(userClient.create(jobseekerId)).willReturn(Optional.of(aUser().w(UserRole.JOBSEEKER).build()));
        new AddJobseekerToJob(jobs, userIdFactory, userClient).with("", jobId.id());
        verify(jobs).addJobseeker(jobId, jobseekerId);
    }

    @Test public void
    throw_authorization_exception_if_user_is_not_a_jobseeker(){
        UserId jobseekerId = aUserId().build();
        given(userIdFactory.create("")).willReturn(jobseekerId);
        given(userClient.create(jobseekerId)).willReturn(Optional.of(aUser().build()));
        assertThrows(AuthorizationException.class, () -> new AddJobseekerToJob(jobs, userIdFactory, userClient).with("", ""));
    }

    

}
