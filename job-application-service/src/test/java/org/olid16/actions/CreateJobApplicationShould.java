package org.olid16.actions;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.entities.JobApplication;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.exceptions.ValidationException;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.JobApplicationBuilder.*;
import static org.olid16.utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobApplicationShould {

    @Mock JobApplications jobApplications;
    @Mock UserClient userClient;
    @Mock JobClient jobClient;

    @Test public void
    return_a_job_application_with_id_when_input_is_right() {
        given(jobClient.create(any())).willReturn(aJob());
        given(userClient.create(any())).willReturn(aUser(UserRole.JOBSEEKER));
        given(jobApplications.add(any())).willReturn(aJobApplication().build());
        JobApplication createdJobApplication = new CreateJobApplication(jobApplications, userClient, jobClient).with(aJobApplication().w(JobApplicationId.create("")).build());
        assertThat(createdJobApplication.id()).isNotEmpty();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_a_jobseeker(){
        given(userClient.create(any())).willReturn(aUser(UserRole.EMPLOYER));
        assertThrows(AuthorizationException.class, () -> new CreateJobApplication(jobApplications, userClient, jobClient).with(aJobApplication().build()));
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_found(){
        given(userClient.create(any())).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> new CreateJobApplication(jobApplications, userClient, jobClient).with(aJobApplication().build()));
    }

    @Test public void
    throw_validation_exception_when_job_is_not_found(){
        given(userClient.create(any())).willReturn(aUser(UserRole.JOBSEEKER));
        given(jobClient.create(any())).willReturn(Optional.empty());
        assertThrows(ValidationException.class, () -> new CreateJobApplication(jobApplications, userClient, jobClient).with(aJobApplication().build()));
    }

    private Optional<Job> aJob() {
        return Optional.of(Job.create(JobId.create(""), Title.create("  ")));
    }

    public Optional<User> aUser(UserRole userRole) {
        return Optional.of(User.create(Person.create(""), userRole, UserId.create("")));
    }
}