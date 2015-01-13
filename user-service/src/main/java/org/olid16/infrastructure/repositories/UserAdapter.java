package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;

public class UserAdapter {

    public DBObject toDBObject(User user){
        return new BasicDBObject("_id", user.id())
                .append("person", new BasicDBObject("name", user.name()))
                .append("role", user.role());
    }
    
    public User fromDBObject(DBObject dbObject){
        return User.createUser(person(dbObject), role(dbObject), userId(dbObject));
        
    }

    private UserId userId(DBObject dbObject) {
        return UserId.create(dbObject.get("_id").toString());
    }

    private UserRole role(DBObject dbObject) {
        return UserRole.valueOf(dbObject.get("role").toString());
    }

    private Person person(DBObject dbObject) {
        return Person.create(((BasicDBObject)dbObject.get("person")).get("name").toString());
    }

}
