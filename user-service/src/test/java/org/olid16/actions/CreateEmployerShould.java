package org.olid16.actions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.User;
import org.olid16.domain.Users;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateEmployerShould {

    @Mock Users users;
    CreateEmployer createEmployer;

    @Before
    public void initialise() throws Exception {
        createEmployer = new CreateEmployer(users);
    }

    @Test public void
    return_a_user_with_employer_role(){
        User user = createEmployer.with("Bob");
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    add_a_employer_into_users(){
        createEmployer.with("Bob");
        verify(users).add(aUser().build());
    }


}
