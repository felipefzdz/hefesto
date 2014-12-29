package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.*;
import org.bson.types.ObjectId;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;
import org.olid16.domain.collections.Users;

import java.util.Optional;

public class MongoUsers implements Users {
    
    private final DBCollection users;
    private final UserAdapter userAdapter;

    @Inject
    public MongoUsers(DB database, UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
        this.users = database.getCollection("users");
    }


    @Override
    public void add(User user) {
        users.insert(userAdapter.toDBObject(user));
    }

    @Override
    public UserId nextId() {
        return UserId.create((ObjectId.get().toString()));
    }

    @Override
    public Optional<String> by(UserId userId) {
        DBObject user = users.find(new BasicDBObject("_id", userId.id())).one();
        return user == null ? Optional.empty() : Optional.of(user.toString());
    }

}
