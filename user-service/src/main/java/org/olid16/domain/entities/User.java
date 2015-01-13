package org.olid16.domain.entities;

import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;

import static org.olid16.domain.values.UserRole.EMPLOYER;

public class User {
    private final Person person;
    private final UserRole role;
    private final UserId userId;

    private User(Person person, UserRole role, UserId userId) {
        this.person = person;
        this.role = role;
        this.userId = userId;
    }

    public static User createUser(Person person, UserRole role, UserId userId) {
        return new User(person, role, userId);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
