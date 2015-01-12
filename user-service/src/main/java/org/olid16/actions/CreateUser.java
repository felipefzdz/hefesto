package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.domain.factories.UserFactory;

public class CreateUser {

    private final Users users;
    private final UserFactory userFactory;

    @Inject
    public CreateUser(Users users, UserFactory userFactory) {
        this.users = users;
        this.userFactory = userFactory;
    }

    public User with(String name, String role) {
        User user = userFactory.create(name, role, users.nextId());
        users.add(user);
        return user;
    }
}
