package org.olid16.domain.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.infrastructure.clients.UserClient;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.olid16.domain.values.UserRole.EMPLOYER;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceShould {

    @Mock UserClient userClient;
    @Mock UserIdFactory userIdFactory;

    @Test
    public void
    return_is_employer_true_when_user_role_is_employer(){
        given(userClient.getBy(null)).willReturn(aUser().w(EMPLOYER).build());
        assertThat(new UserService(userClient, userIdFactory).isEmployer(null)).isTrue();
    }

    @Test
    public void
    return_is_employer_false_when_user_role_is_not_employer(){
        given(userClient.getBy(null)).willReturn(aUser().build());
        assertThat(new UserService(userClient, userIdFactory).isEmployer(null)).isFalse();
    }
}
