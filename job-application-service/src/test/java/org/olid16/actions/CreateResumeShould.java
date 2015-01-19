package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.olid16.builders.ResumeBuilder.aResume;
import static org.olid16.utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateResumeShould {

    @Mock Resumes resumes;
    @Mock UserClient userClient;

    @Test public void
    return_a_resume_with_id_when_input_is_right() {
        given(userClient.create(any())).willReturn(aUser(UserRole.JOBSEEKER));
        given(resumes.add(any())).willReturn(aResume().build());
        Resume createdResume = new CreateResume(resumes, userClient).with(aResume().w(ResumeId.create("")).build());
        assertThat(createdResume.id()).isNotEmpty();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_a_jobseeker(){
        given(userClient.create(any())).willReturn(aUser(UserRole.EMPLOYER));
        assertThrows(AuthorizationException.class, () -> new CreateResume(resumes, userClient).with(aResume().build()));
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_found(){
        given(userClient.create(any())).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> new CreateResume(resumes, userClient).with(aResume().build()));
    }

    public Optional<User> aUser(UserRole userRole) {
        return Optional.of(User.create(Person.create(""), userRole, UserId.create("")));
    }
}