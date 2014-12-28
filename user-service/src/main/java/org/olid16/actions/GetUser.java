package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.domain.factories.UserFactory;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.Optional;

public class GetUser {
    private final Users users;

    @Inject
    public GetUser(Users users) {
        this.users = users;
    }

    public Optional<User> by(JsonEntity jsonEntity) {
        return users.by(UserId.create(jsonEntity.get("userId")));
    }
}
