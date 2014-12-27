package org.olid16.domain.factories;

import org.olid16.domain.entities.User;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.domain.values.Person;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.base.Strings.*;

public class UserFactory {

    public User create(JsonEntity jsonEntity, UserId userId){
        return new User(
                createName(jsonEntity),
                createRole(jsonEntity),
                userId);
    }

    private Person createName(JsonEntity jsonEntity) {
        validatePresence(jsonEntity, "name");
        return Person.create(jsonEntity.get("name"));
    }

    private UserRole createRole(JsonEntity jsonEntity) {
        validatePresence(jsonEntity, "role");
        try {
            return UserRole.valueOf(jsonEntity.get("role").toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException(String.format("Role %s is not valid", jsonEntity.get("role")));
        }
    }

    private void validatePresence(JsonEntity jsonEntity, String field) {
        try {
            if(isNullOrEmpty(jsonEntity.get(field))){
                throw new ValidationException(String.format("%s is mandatory", field));
            }
        } catch (NullPointerException e) {
            throw new ValidationException(String.format("%s is mandatory", field));
        }
    }
}
