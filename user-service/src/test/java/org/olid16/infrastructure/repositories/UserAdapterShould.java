package org.olid16.infrastructure.repositories;

import com.mongodb.DBObject;
import org.junit.Test;

import static builders.UserBuilder.aUser;
import static com.google.common.truth.Truth.assertThat;

public class UserAdapterShould {

    @Test
    public void
    create_a_dbObject_with_id(){
        DBObject dbObject = new UserAdapter().toDBObject(aUser().build());
        assertThat(dbObject.get("_id")).isNotNull();

    }
}
