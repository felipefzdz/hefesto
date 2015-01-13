package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.exceptions.ValidationException;

import java.util.Optional;

public class UpdateUser {
    private final Users users;

    @Inject
    public UpdateUser(Users users) {
        this.users = users;
    }

    public void with(String userId, String name) {
        Optional<User> oldUser = users.by(UserId.create(userId));
        if(oldUser.isPresent()) {
            if (!oldUser.get().name().equals(name)) {
                users.update(createUser(name, oldUser.get()));
                return;
            }
            throw new ValidationException("Name should be different to update");
        }
        throw new ValidationException("User should exist in order to be updated");
        
    }

    private User createUser(String name, User oldUser) {
        return User.createUser(
                Person.create(name), 
                UserRole.valueOf(oldUser.role()),
                UserId.create(oldUser.id()));
    }
}
