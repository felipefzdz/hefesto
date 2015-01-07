package org.olid16.actions;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.factories.JobFactory;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.Optional;

import static builders.JobBuilder.aJob;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class CreateJobShould {

    @Mock UserFactory userFactory;
    @Mock JobFactory jobFactory;
    @Mock Jobs jobs;

    @Test public void
    return_job_when_user_is_employer(){
        given(userFactory.create(null)).willReturn(Optional.of(UserBuilder.aUser().w(UserRole.EMPLOYER).build()));
        given(jobFactory.create(any(JsonEntity.class), anyObject())).willReturn(aJob().build());
        Job job = new CreateJob(userFactory, jobFactory, jobs).with(null);
        assertThat(job).isNotNull();
    }

    @Test public void
    throw_authorization_exception_when_user_is_not_employer(){
        given(userFactory.create(null)).willReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> new CreateJob(userFactory, jobFactory, jobs).with(null));
    }
}
