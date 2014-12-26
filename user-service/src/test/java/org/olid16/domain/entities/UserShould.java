package org.olid16.domain.entities;

import org.junit.Test;
import org.olid16.domain.entities.User;

import static builders.UserBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.olid16.domain.values.UserRole.*;

public class UserShould {
    
    @Test public void
    be_an_employer_when_employer_role_provided(){
        User user = aUser().w(EMPLOYER).build();
        assertThat(user.isEmployer()).isTrue();
    }

    @Test public void
    not_be_an_employer_when_unknown_role_provided(){
        User user = aUser().w(UNKNOWN).build();
        assertThat(user.isEmployer()).isFalse();
    }
}
