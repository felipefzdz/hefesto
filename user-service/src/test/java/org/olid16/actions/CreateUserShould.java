package org.olid16.actions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.infrastructure.rest.JsonEntity;
import org.olid16.domain.collections.Users;
import utils.Assert;

import static builders.UserBuilder.UserIdBuilder.aUserId;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserShould {

    @Mock Users users;
    @Mock
    JsonEntity jsonEntity;
    CreateUser createUser;

    @Before
    public void initialise() throws Exception {
        createUser = new CreateUser(users);
    }

    @Test public void
    return_an_employer_when_employer_role(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willReturn("employer");
        User user = createUser.with(jsonEntity);
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    throw_validation_exception_when_invalid_role(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willReturn("invalidRole");
        Assert.assertThrows(ValidationException.class, () -> createUser.with(jsonEntity));
    }

    @Test public void
    add_a_employer_into_users(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willReturn("employer");
        given(users.nextId()).willReturn(aUserId().build());
        createUser.with(jsonEntity);
        verify(users).add(aUser().build());
    }


}
