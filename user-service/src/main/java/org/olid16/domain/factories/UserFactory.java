package org.olid16.domain.factories;

import com.google.common.base.Strings;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.exceptions.ValidationException;

public class UserFactory {

    public User create(String name, String role, UserId userId) {
        validatePresenceOf(name, role);
        return new User(
                createName(name),
                createRole(role),
                userId);
    }

    private UserRole createRole(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException(String.format("Role %s is not valid", role));
        }
    }

    private Person createName(String name) {
        return Person.create(name);
    }

    private void validatePresenceOf(String... inputs) {
        for(String input: inputs){
            if(Strings.isNullOrEmpty(input)){
                throw new ValidationException(String.format("%s is mandatory", input));
            }
        }
        
    }
}
