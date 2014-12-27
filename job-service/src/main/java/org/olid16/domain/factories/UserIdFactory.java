package org.olid16.domain.factories;

import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.JsonEntity;

public class UserIdFactory {
    public UserId create(JsonEntity jsonEntity) {
        jsonEntity.validatePresenceOf("userId");
        return UserId.create(jsonEntity.get("userId"));
    }
}
