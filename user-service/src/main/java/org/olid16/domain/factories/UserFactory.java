package org.olid16.domain.factories;

import org.olid16.domain.entities.User;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.rest.JsonEntity;

public class UserFactory {

    public User create(JsonEntity jsonEntity, UserId userId){
        jsonEntity.validatePresenceOf("role", "name");
        return new User(
                createName(jsonEntity),
                createRole(jsonEntity),
                userId);
    }
    
    private Person createName(JsonEntity jsonEntity) {
        return Person.create(jsonEntity.get("name"));
    }

    private UserRole createRole(JsonEntity jsonEntity) {
        try {
            return UserRole.valueOf(jsonEntity.get("role").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException(String.format("Role %s is not valid", jsonEntity.get("role")));
        }
    }


}
