package org.olid16.actions;

import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.Optional;

import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class GetUserShould {

    @Mock Users users;
    @Mock JsonEntity jsonEntity;

    @Test public void
    return_a_user_when_exists_by_id(){
        given(users.by(aUserId().build())).willReturn(Optional.of(""));
        Optional<String> user = new GetUser(users).by("1234");
        assertThat(user.isPresent()).isTrue();
    }
    
    @Test public void
    return_empty_optional_when_not_exist_by_id(){
        given(users.by(aUserId().build())).willReturn(Optional.empty());
        Optional<String> user = new GetUser(users).by("1234");
        assertThat(user.isPresent()).isFalse();
    }
}
