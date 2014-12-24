package org.olid16.actions;

import org.olid16.domain.User;
import org.olid16.domain.Users;

import static org.olid16.domain.User.createUser;

public class CreateEmployer {

    private final Users users;

    public CreateEmployer(Users users) {
        this.users = users;
    }

    public User with(String name) {
        User user = createUser(name);
        users.add(user);
        return user;
    }
}
