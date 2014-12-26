package org.olid16.actions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.User;
import org.olid16.domain.UserJson;
import org.olid16.domain.Users;

import static builders.UserBuilder.UserIdBuilder.aUserId;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateEmployerShould {

    @Mock Users users;
    @Mock UserJson userJson;
    CreateEmployer createEmployer;

    @Before
    public void initialise() throws Exception {
        createEmployer = new CreateEmployer(users);
    }

    @Test public void
    return_a_user_with_employer_role(){
        given(userJson.get(anyString())).willReturn("Bob");
        User user = createEmployer.with(userJson);
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    add_a_employer_into_users(){
        given(userJson.get(anyString())).willReturn("Bob");
        given(users.nextId()).willReturn(aUserId().build());
        createEmployer.with(userJson);
        verify(users).add(aUser().build());
    }


}
