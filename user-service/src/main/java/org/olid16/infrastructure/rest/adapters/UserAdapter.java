package org.olid16.infrastructure.rest.adapters;

import org.olid16.infrastructure.rest.entities.User;

public class UserAdapter {
    public User fromDomain(org.olid16.domain.entities.User user) {
        return new User(user.name(), user.role(), user.id());
    }
}
