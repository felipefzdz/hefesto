package org.olid16.infrastructure.rest.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.adapters.UserAdapter;
import org.olid16.infrastructure.rest.entities.User;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static builders.UserBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceShould {

    @Mock CreateUser createUser;
    @Mock GetUser getUser;
    @Mock UserAdapter userAdapter;

    @Test public void
    return_user_id_when_user_exists(){
        org.olid16.domain.entities.User aUser = aUser().build();
        given(getUser.by("")).willReturn(Optional.of(aUser));
        given(userAdapter.fromDomain(aUser)).willReturn(new User(null, null, "1234"));
        User user = (User)new UserResource(createUser, getUser, userAdapter).getByUserId("").getEntity();
        assertThat(user.getId()).isEqualTo("1234");
    }

    @Test public void
    throw_web_application_exception_when_user_not_exists(){
        doThrow(WebApplicationException.class).when(getUser).by("");
        assertThrows(WebApplicationException.class, () -> new UserResource(createUser, getUser, userAdapter).getByUserId(""));
    }

    @Test public void
    throw_web_application_exception_when_problem_during_creation(){
        doThrow(DomainException.class).when(createUser).with("", "");
        assertThrows(WebApplicationException.class, () -> new UserResource(createUser, getUser, userAdapter).create(new User("", "", "")));
    }
    
    
}
