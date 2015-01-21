package org.olid16.infrastructure.clients;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommand;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserAdapter;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.clients.UserClient;
import retrofit.RetrofitError;

import java.util.Optional;

import static builders.UserBuilder.UserIdBuilder.aUserId;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class UserClientShould {

    @Mock GetUserByIdCommandFactory factory;
    @Mock UserAdapter userAdapter;
    @Mock GetUserByIdCommand getUserByIdCommand;

    @Test public void
    return_employer_present_when_user_role_is_employer(){
        Optional<User> user = Optional.of(new User("1234", "Bob", "Employer"));
        given(userAdapter.fromClient(user.get())).willReturn(aUser().build());
        given(factory.create(anyString())).willReturn(getUserByIdCommand);
        given(getUserByIdCommand.execute()).willReturn(user);
        assertThat(new UserClient(userAdapter, factory).create(aUserId().build()).isPresent()).isTrue();
    }

    @Test public void
    return_employer_false_when_user_does_not_exist(){
        given(factory.create(anyString())).willReturn(getUserByIdCommand);
        given(getUserByIdCommand.execute()).willReturn(Optional.empty());
        assertThat(new UserClient(userAdapter, factory).create(aUserId().build()).isPresent()).isFalse();
    }
}

