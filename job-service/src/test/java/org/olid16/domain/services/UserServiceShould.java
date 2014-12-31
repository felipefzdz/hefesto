package org.olid16.domain.services;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;

import static builders.UserBuilder.UserIdBuilder.*;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.olid16.domain.values.UserRole.EMPLOYER;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

    @Mock UserApi userApi;
    @Mock UserIdFactory userIdFactory;

    @Test
    public void
    return_is_employer_true_when_user_role_is_employer(){
        given(userIdFactory.create(any(JsonEntity.class))).willReturn(aUserId().build());
        given(userApi.getBy(anyString())).willReturn(aUser().w(EMPLOYER).build());
        assertThat(new UserService(userApi, userIdFactory).isEmployer(null)).isTrue();
    }

    @Test
    public void
    return_is_employer_false_when_user_role_is_not_employer(){
        given(userIdFactory.create(any(JsonEntity.class))).willReturn(aUserId().build());
        given(userApi.getBy(anyString())).willReturn(aUser().build());
        assertThat(new UserService(userApi, userIdFactory).isEmployer(null)).isFalse();
    }
}
