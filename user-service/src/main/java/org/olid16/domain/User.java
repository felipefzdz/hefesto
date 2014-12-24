package org.olid16.domain;

public class User {
    private final Person person;

    public User(Person person) {
        this.person = person;
    }

    public boolean isEmployer() {
        return true;
    }
    
    public static User createUser(String name){
        return new User(new Person(name));
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
