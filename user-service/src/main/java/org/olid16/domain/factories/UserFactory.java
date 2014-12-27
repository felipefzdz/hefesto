package org.olid16.domain.factories;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
        if(isNullOrEmpty(jsonEntity.get("name"))){
            throw new ValidationException("Name is mandatory");
        }
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
