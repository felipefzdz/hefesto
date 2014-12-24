package org.olid16.domain;

import static org.olid16.domain.UserRole.*;

public class User {
    private final Person person;
    private final UserRole role;

    public User(Person person, UserRole role) {
        this.person = person;
        this.role = role;
    }

    public boolean isEmployer() {
        return EMPLOYER.equals(role);
    }
    
    public static User createUser(String name, UserRole userRole){
        return new User(new Person(name), userRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!person.equals(user.person)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return person.hashCode();
    }
}
