package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.domain.services.UserService;
import org.olid16.infrastructure.rest.JsonEntity;

import static builders.JobBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobShould {

    @Mock UserService userService;
    @Mock JobFactory jobFactory;
    @Mock Jobs jobs;

    @Test public void
    return_job_when_user_is_employer(){
        given(userService.isEmployer(null)).willReturn(true);
        given(jobFactory.create(any(JsonEntity.class), anyObject())).willReturn(aJob().build());
        Job job = new CreateJob(userService, jobFactory, jobs).with(null);
        assertThat(job).isNotNull();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_employer(){
        given(userService.isEmployer(null)).willReturn(false);
        assertThrows(AuthorizationException.class, () -> new CreateJob(userService, jobFactory, jobs).with(null));
    }
}
