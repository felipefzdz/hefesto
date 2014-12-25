package org.olid16.infrastructure.repositories;

import org.olid16.domain.User;
import org.olid16.domain.UserId;
import org.olid16.domain.Users;

import java.util.Map;

public class InMemoryUsers implements Users {
    private final Map<UserId, User> users;

    public InMemoryUsers(Map<UserId, User> users) {
        this.users = users;
    }

    @Override
    public void add(User user) {
        users.put(user.userId(), user);
    }

    @Override
    public UserId nextId() {
        int id = users.keySet().stream()
                .map(userId -> userId.id())
                .max(Integer::compare)
                .orElse(0)
                + 1;
        return new UserId(id);
    }
}
