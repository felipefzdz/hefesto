package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.User;
import org.olid16.domain.Users;

import static org.olid16.domain.User.createUser;
import static org.olid16.domain.UserRole.EMPLOYER;

public class CreateEmployer {

    private final Users users;

    @Inject
    public CreateEmployer(Users users) {
        this.users = users;
    }

    public User with(String name) {
        User user = createUser(name, EMPLOYER, users.nextId());
        users.add(user);
        return user;
    }
}
