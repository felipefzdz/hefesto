package org.olid16.infrastructure.clients;

import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;

public class UserAdapter {
    public org.olid16.domain.values.User fromClient(User user) {
        return org.olid16.domain.values.User.create(
                Person.create(user.getName()),
                userRole(user),
                UserId.create(user.getId()));
    }

    private UserRole userRole(User user) {
        try {
            return UserRole.valueOf(user.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserRole.UNKNOWN;
        }
    }

}
