package org.olid16.domain.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;
import retrofit.RetrofitError;

import static builders.UserBuilder.UserIdBuilder.aUserId;
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

    @Test public void
    return_employer_true_when_user_role_is_employer(){
        given(userIdFactory.create(any(JsonEntity.class))).willReturn(aUserId().build());
        given(userApi.getBy(anyString())).willReturn(aUser().w(EMPLOYER).build());
        assertThat(new UserService(userApi, userIdFactory).isEmployer(null)).isTrue();
    }

    @Test public void
    return_employer_false_when_user_role_is_not_employer(){
        given(userIdFactory.create(any(JsonEntity.class))).willReturn(aUserId().build());
        given(userApi.getBy(anyString())).willReturn(aUser().build());
        assertThat(new UserService(userApi, userIdFactory).isEmployer(null)).isFalse();
    }

    @Test public void
    return_employer_false_when_user_does_not_exist(){
        given(userIdFactory.create(any(JsonEntity.class))).willReturn(aUserId().build());
        given(userApi.getBy(anyString())).willThrow(RetrofitError.class);
        assertThat(new UserService(userApi, userIdFactory).isEmployer(null)).isFalse();
    }
}
