package org.olid16.infrastructure.circuit_breaker.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserApi;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetUserByIdCommandShould {

    @Mock UserApi userApi;

    @Test public void
    call_user_api_with_user_id() throws Exception {
        String userId = "1234";
        new GetUserByIdCommand(userApi, userId).run();
        verify(userApi).getBy(userId);
    }
    
    @Test public void
    return_empty_user_in_fallback(){
        User user = new GetUserByIdCommand(userApi, null).getFallback();
        assertThat(user.getId()).is("");
    }
}