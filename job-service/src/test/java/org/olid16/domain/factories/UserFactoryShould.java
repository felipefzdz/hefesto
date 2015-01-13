package org.olid16.domain.factories;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserAdapter;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.RetrofitError;

import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.aUserId;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.olid16.domain.values.UserRole.EMPLOYER;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryShould {

    @Mock UserApi userApi;
    @Mock UserAdapter userAdapter;

    @Test public void
    return_employer_present_when_user_role_is_employer(){
        User user = new User("1234", "Bob", "Employer");
        given(userAdapter.fromClient(user)).willReturn(aUser().build());
        given(userApi.getBy(anyString())).willReturn(user);
        assertThat(new UserFactory(userApi, userAdapter).create(aUserId().build()).isPresent()).isTrue();
    }

    @Test public void
    return_employer_false_when_user_does_not_exist(){
        given(userApi.getBy(anyString())).willThrow(RetrofitError.class);
        assertThat(new UserFactory(userApi, userAdapter).create(aUserId().build()).isPresent()).isFalse();
    }
}

