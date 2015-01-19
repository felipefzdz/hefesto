package org.olid16.infrastructure.clients;

import org.junit.Test;
import org.olid16.domain.values.User;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.domain.values.UserRole.UNKNOWN;

public class UserAdapterShould {
    
    @Test public void
    adapt_from_client(){
        User user = new UserAdapter().fromClient(new org.olid16.infrastructure.clients.User("1234", "name", "employer"));
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    return_unknown_role_when_invalid_role(){
        org.olid16.infrastructure.clients.User user = new org.olid16.infrastructure.clients.User("1234", "name", "");
        assertThat(new UserAdapter().fromClient(user).userRole()).isEqualTo(UNKNOWN);
    }

}