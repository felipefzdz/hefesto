package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.User;

public class UserAdapter {

    public DBObject toDBObject(User user){
        return new BasicDBObject("_id", user.id())
                .append("person", new BasicDBObject("name", user.name()))
                .append("role", user.role());

    }

    public  User fromDBObject(DBObject dbObject) {
        return null;
    }
}
