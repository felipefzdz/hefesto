package org.olid16.infrastructure.clients;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.exceptions.DomainException;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import java.io.IOException;

import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.aUserId;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class UserClientShould {

    @Mock Resty resty;
    @Mock UserAdapter userAdapter;

    @Test public void
    throw_domain_exception_when_the_communication_was_wrong() throws IOException {
        given(resty.json(anyString())).willThrow(IOException.class);
        assertThrows(DomainException.class, () -> new UserClient(resty, userAdapter).getBy(aUserId().build()));
        
    }

    @Test public void
    return_user_when_response_is_fine() throws IOException {
        given(resty.json(anyString())).willReturn(new JSONResource());
        given(userAdapter.from(any(JSONResource.class))).willReturn(aUser().build());
        User user = new UserClient(resty, userAdapter).getBy(aUserId().build());
        assertThat(user).isEqualTo(aUser().build());
        
    }
}
