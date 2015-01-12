package org.olid16.infrastructure.rest.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.entities.User;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceShould {

    @Mock CreateUser createUser;
    @Mock GetUser getUser;

    @Test public void
    return_user_id_when_user_exists(){
        given(getUser.by("")).willReturn(Optional.of("1234"));
        String user = new UserResource(createUser, getUser).getByUserId("");
        assertThat(user).isEqualTo("1234");
    }
    
    @Test public void
    throw_web_application_exception_when_user_not_exists(){
        doThrow(WebApplicationException.class).when(getUser).by("");
        assertThrows(WebApplicationException.class, () -> new UserResource(createUser, getUser).getByUserId(""));
    }

    @Test public void
    throw_web_application_exception_when_problem_during_creation(){
        doThrow(DomainException.class).when(createUser).with("", "");
        assertThrows(WebApplicationException.class, () -> new UserResource(createUser, getUser).create(new User("", "")));
    }
    
    
}
