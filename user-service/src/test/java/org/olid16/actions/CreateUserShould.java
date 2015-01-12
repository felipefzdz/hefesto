package org.olid16.actions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.UserId;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.olid16.domain.values.UserRole.EMPLOYER;
import static utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserShould {

    @Mock Users users;
    @Mock UserFactory userFactory;
    CreateUser createUser;

    @Before
    public void initialise() throws Exception {
        createUser = new CreateUser(users, userFactory);
    }

    @Test public void
    return_an_employer_when_employer_role(){
        given(userFactory.create(anyString(), anyString(), any(UserId.class))).willReturn(aUser().w(EMPLOYER).build());
        User user = createUser.with("", "");
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    throw_validation_exception_when_factory_throws_exception(){
        given(userFactory.create(anyString(), anyString(),  any(UserId.class))).willThrow(ValidationException.class);
        assertThrows(ValidationException.class, () -> createUser.with("", ""));
    }

    @Test public void
    add_a_employer_into_users(){
        User user = aUser().w(EMPLOYER).build();
        given(userFactory.create(anyString(), anyString(), any(UserId.class))).willReturn(user);
        createUser.with("", "");
        verify(users).add(user);
    }


}
