package org.olid16.domain.entities;

import org.olid16.domain.exceptions.ValidationException;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.rest.JsonEntity;

import static org.olid16.domain.values.UserRole.*;

public class User {
    private final Person person;
    private final UserRole role;
    private final UserId userId;

    public User(Person person, UserRole role, UserId userId) {
        this.person = person;
        this.role = role;
        this.userId = userId;
    }

    public boolean isEmployer() {
        return EMPLOYER.equals(role);
    }

    public String name() {
        return person.name();
    }

    public String role() {
        return role.toString();
    }

    public String id() {
        return userId.id();
    }
}
