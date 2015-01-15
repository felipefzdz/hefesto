package org.olid16.infrastructure.repositories;

import builders.UserBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;

import java.util.Optional;

import static builders.UserBuilder.*;
import static builders.UserBuilder.UserIdBuilder.aUserId;
import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MongoUsersShould {

    @Mock DBCollection dbCollection;
    @Mock UserAdapter userAdapter;
    @Mock DBCursor dbCursor;

    @Test public void
    insert_a_user(){
        new MongoUsers(dbCollection, userAdapter).add(aUser().build());
        verify(dbCollection).insert(any(DBObject[].class));
    }

    @Test public void
    return_a_user_when_exists_by_id(){
        given(dbCollection.find(any(DBObject.class))).willReturn(dbCursor);
        given(dbCursor.one()).willReturn(mock(DBObject.class));
        given(userAdapter.fromDBObject(any(DBObject.class))).willReturn(aUser().build());
        Optional<User> user = new MongoUsers(dbCollection, userAdapter).by(aUserId().build());
        assertThat(user.isPresent()).isTrue();
    }

    @Test public void
    return_empty_optional_when_not_exist_by_id(){
        given(dbCollection.find(any(DBObject.class))).willReturn(dbCursor);
        given(dbCursor.one()).willReturn(null);
        Optional<User> user = new MongoUsers(dbCollection, userAdapter).by(aUserId().build());
        assertThat(user.isPresent()).isFalse();
    }

    @Test public void
    return_different_ids(){
        UserId userId1 = new MongoUsers(null, null).nextId();
        UserId userId2 = new MongoUsers(null, null).nextId();
        assertThat(userId1).isNotEqualTo(userId2);
    }

    @Test public void
    update_user(){
        new MongoUsers(dbCollection, null).update(aUser().build());
        verify(dbCollection).update(any(), any());
    }
    
}
