package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;
import org.olid16.domain.collections.Users;

public class MongoUsers implements Users {
    
    private final DBCollection users;

    @Inject
    public MongoUsers(DB database) {
        this.users = database.getCollection("users");
    }


    @Override
    public void add(User user) {
        users.insert(toDBObject(user));
    }

    @Override
    public UserId nextId() {
        return UserId.create((ObjectId.get().toString()));
    }
    
    private DBObject toDBObject(User user){
        return new BasicDBObject("_id", user.id())
                .append("person", new BasicDBObject("name", user.name()))
                .append("role", user.role());

    }
}
