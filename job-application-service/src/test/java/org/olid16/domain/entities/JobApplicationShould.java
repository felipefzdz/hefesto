package org.olid16.domain.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.olid16.builders.JobApplicationBuilder.aJobApplication;
import static org.olid16.utils.Assert.assertNotThrows;
import static org.olid16.utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationShould {

    @Mock UserClient userClient;
    @Mock JobClient jobClient;

    @Test public void
    be_valid_when_input_is_right(){
        given(jobClient.create(any())).willReturn(aJob());
        given(userClient.create(any())).willReturn(aUser(UserRole.JOBSEEKER));
        assertNotThrows(() -> aJobApplication().w(userClient).w(jobClient).build().validate());
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_a_jobseeker(){
        given(userClient.create(any())).willReturn(aUser(UserRole.EMPLOYER));
        assertThrows(AuthorizationException.class, () -> aJobApplication().w(userClient).w(jobClient).build().validate());
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_found(){
        given(userClient.create(any())).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> aJobApplication().w(userClient).w(jobClient).build().validate());
    }

    @Test public void
    throw_validation_exception_when_job_is_not_found(){
        given(userClient.create(any())).willReturn(aUser(UserRole.JOBSEEKER));
        given(jobClient.create(any())).willReturn(Optional.empty());
        assertThrows(ValidationException.class, () -> aJobApplication().w(userClient).w(jobClient).build().validate());
    }

    private Optional<Job> aJob() {
        return Optional.of(Job.create(JobId.create(""), Title.create("  ")));
    }

    public Optional<User> aUser(UserRole userRole) {
        return Optional.of(User.create(Person.create(""), userRole, UserId.create("")));
    }
}