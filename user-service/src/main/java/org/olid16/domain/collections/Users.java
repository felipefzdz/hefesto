package org.olid16.domain.collections;

import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;

import java.util.Optional;

public interface Users {
    void add(User user);

    UserId nextId();

    Optional<User> by(UserId userId);
}
