package org.olid16.infrastructure.circuit_breaker.commands;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserApi;

import java.util.Optional;

import static builders.UserBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUserByIdCommandShould {

    @Mock UserApi userApi;

    @Test public void
    call_user_api_with_user_id() throws Exception {
        given(userApi.getBy(any())).willReturn(new User("", "", ""));
        String userId = "1234";
        new GetUserByIdCommand(userApi, userId).run();
        verify(userApi).getBy(userId);
    }
    
    @Test public void
    return_not_present_user_in_fallback(){
        Optional<User> user = new GetUserByIdCommand(userApi, null).getFallback();
        assertThat(user.isPresent()).isFalse();
    }
}