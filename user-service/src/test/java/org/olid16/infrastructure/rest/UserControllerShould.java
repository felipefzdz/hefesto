package org.olid16.infrastructure.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateUser;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.infrastructure.rest.controllers.UserController;
import spark.Request;


import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerShould {

    @Mock CreateUser createUser;
    @Mock Request request;

    @Test public void
    call_create_user(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willReturn(aUser().build());
        new UserController(createUser).create(request, null);
        verify(createUser).with(any(JsonEntity.class));
    }

    @Test public void
    return_user_id(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willReturn(aUser().build());
        String message = new UserController(createUser).create(request, null);
        assertThat(message).is("1234");
    }

    @Test public void
    return_validation_message_error_when_invalid_role(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willThrow(new ValidationException("Role invalidOne is not valid"));
        String message = new UserController(createUser).create(request, null);
        assertThat(message).is("Role invalidOne is not valid");
        
    }
}
