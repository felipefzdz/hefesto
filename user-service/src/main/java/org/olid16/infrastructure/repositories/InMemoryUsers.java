package org.olid16.infrastructure.repositories;

import org.olid16.domain.User;
import org.olid16.domain.Users;

import java.util.Set;

public class InMemoryUsers implements Users {
    private final Set<User> users;

    public InMemoryUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }
}
