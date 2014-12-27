package org.olid16.infrastructure.rest.controllers;

import com.google.inject.Inject;
import org.olid16.actions.CreateUser;
import org.olid16.domain.entities.User;
import org.olid16.domain.exceptions.DomainException;
import org.olid16.infrastructure.rest.JsonEntity;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;

public class UserController {
    private final CreateUser createUser;

    @Inject
    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    public String create(Request req, Response res) {
        JsonEntity jsonEntity = new JsonEntity(readFrom(req.body()));
        try {
            User user = createUser.with(jsonEntity);
            return user.id();
        } catch (DomainException e) {
            return e.getMessage();
        }
    }
}
