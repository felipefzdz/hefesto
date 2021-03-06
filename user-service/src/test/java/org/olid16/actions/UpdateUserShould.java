package org.olid16.actions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.domain.events.UpdateUserEvent;
import org.olid16.domain.values.Person;
import org.olid16.infrastructure.events.UserEventPublisher;
import org.olid16.infrastructure.exceptions.ValidationException;

import java.util.Optional;

import static builders.UserBuilder.UserIdBuilder.*;
import static builders.UserBuilder.aUser;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserShould {

    @Mock Users users;
    @Mock UserEventPublisher userEventPublisher;

    @Test public void
    call_users_update_when_name_is_new(){
        given(users.by(aUserId().build())).willReturn(Optional.of(aUser().build()));
        User user = aUser().w(Person.create("Matt")).build();
        new UpdateUser(users, userEventPublisher).with(user.id(), user.name());
        verify(users).update(user);
    }
    
    @Test public void
    throw_validation_exception_when_name_is_the_same(){
        User user = aUser().build();
        given(users.by(aUserId().build())).willReturn(Optional.of(user));
        assertThrows(ValidationException.class, () -> new UpdateUser(users, userEventPublisher).with(user.id(), user.name()));
    }

    @Test public void
    throw_validation_exception_when_user_not_exists(){
        User user = aUser().build();
        given(users.by(aUserId().build())).willReturn(Optional.empty());
        assertThrows(ValidationException.class, () -> new UpdateUser(users, userEventPublisher).with(user.id(), user.name()));
    }
    
    @Test public void
    publish_update_user_event_when_name_is_different(){
        given(users.by(aUserId().build())).willReturn(Optional.of(aUser().build()));
        User user = aUser().w(Person.create("Matt")).build();
        new UpdateUser(users, userEventPublisher).with(user.id(), user.name());
        verify(userEventPublisher).publish(UpdateUserEvent.createUpdateUserEvent(user));
        
    }
}
