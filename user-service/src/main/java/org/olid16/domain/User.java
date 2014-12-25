package org.olid16.domain;

import static org.olid16.domain.UserRole.*;

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
    
    public static User createUser(String name, UserRole userRole, UserId userId){
        return new User(new Person(name), userRole, userId);
    }

    public UserId userId() {
        return userId;
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
