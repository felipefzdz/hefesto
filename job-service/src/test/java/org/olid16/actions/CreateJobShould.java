package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.Job;
import org.olid16.domain.exceptions.AuthorizationException;
import org.olid16.domain.services.UserService;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobShould {

    @Mock UserService userService;

    @Test public void
    return_job_when_user_is_employer(){
        given(userService.isEmployer(null)).willReturn(true);
        Job job = new CreateJob(userService).with(null);
        assertThat(job).isNotNull();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_employer(){
        given(userService.isEmployer(null)).willReturn(false);
        assertThrows(AuthorizationException.class, () -> new CreateJob(userService).with(null));
    }
}
