package org.olid16.infrastructure.repositories;

import builders.UserBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;

import java.util.Optional;

import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.*;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MongoUsersShould {

    @Mock DBCollection users;
    @Mock DB db;
    @Mock UserAdapter userAdapter;
    @Mock DBCursor dbCursor;

    @Test public void
    insert_a_user(){
        given(db.getCollection(anyString())).willReturn(users);
        new MongoUsers(db, userAdapter).add(aUser().build());
        verify(users).insert(any(DBObject[].class));
    }

    @Test public void
    return_a_user_when_exists_by_id(){
        given(db.getCollection(anyString())).willReturn(users);
        given(users.find(any(DBObject.class))).willReturn(dbCursor);
        given(dbCursor.one()).willReturn(mock(DBObject.class));
        Optional<String> user = new MongoUsers(db, userAdapter).by(aUserId().build());
        assertThat(user.isPresent()).isTrue();
    }

    @Test public void
    return_empty_optional_when_not_exist_by_id(){
        given(db.getCollection(anyString())).willReturn(users);
        given(users.find(any(DBObject.class))).willReturn(dbCursor);
        given(dbCursor.one()).willReturn(null);
        Optional<String> user = new MongoUsers(db, userAdapter).by(aUserId().build());
        assertThat(user.isPresent()).isFalse();
    }
    
}
