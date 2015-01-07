package org.olid16.infrastructure.rest;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.controllers.UserController;
import spark.Request;
import spark.RequestResponseFactory;
import spark.Response;

import java.io.IOException;
import java.util.Optional;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static utils.FileReader.read;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerShould {

    @Mock CreateUser createUser;
    @Mock Request request;
    @Mock GetUser getUser;

    @Test public void
    call_create_user(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willReturn(aUser().build());
        new UserController(createUser, getUser).create(request, null);
        verify(createUser).with(any(JsonEntity.class));
    }

    @Test public void
    return_user_id_when_create_user(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willReturn(aUser().build());
        String message = new UserController(createUser, getUser).create(request, null);
        assertThat(message).is("1234");
    }

    @Test public void
    return_validation_message_error_when_invalid_role(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willThrow(new ValidationException("Role invalidOne is not valid"));
        String message = new UserController(createUser, getUser).create(request, dummyResponse());
        assertThat(message).is("Role invalidOne is not valid");
    }

    @Test public void
    return_bad_request_in_response_when_request_is_invalid(){
        given(request.body()).willReturn("{}");
        given(createUser.with(any(JsonEntity.class))).willThrow(new ValidationException(""));
        Response response = spy(dummyResponse());
        new UserController(createUser, getUser).create(request, response);
        verify(response).status(HttpStatus.BAD_REQUEST_400);
        
    }

    @Test public void
    return_user_when_exists_by_id() throws IOException {
        given(request.body()).willReturn("{}");
        given(getUser.by(anyString())).willReturn(Optional.of(read("user.json")));
        String user = new UserController(null, getUser).get(request, null);
        assertThat(user).isEqualTo(read("user.json"));
    }

    @Test public void
    return_empty_when_not_exists_by_id() throws IOException {
        given(request.body()).willReturn("{}");
        given(getUser.by(anyString())).willReturn(Optional.empty());
        String message = new UserController(null, getUser).get(request, dummyResponse());
        assertThat(message).isEqualTo("{}");
    }

    @Test public void
    return_not_found_response_when_not_exists_by_id() throws IOException {
        given(request.body()).willReturn("{}");
        given(getUser.by(anyString())).willReturn(Optional.empty());
        Response response = spy(dummyResponse());
        new UserController(null, getUser).get(request, response);
        verify(response).status(HttpStatus.NOT_FOUND_404);

    }

    private Response dummyResponse() {
        return RequestResponseFactory.create(new org.eclipse.jetty.server.Response(null, null));
    }
}
