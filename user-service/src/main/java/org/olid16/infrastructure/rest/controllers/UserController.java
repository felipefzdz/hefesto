package org.olid16.infrastructure.rest.controllers;

import com.google.inject.Inject;
import org.olid16.actions.CreateUser;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.JsonEntity;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;
import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

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
            res.status(BAD_REQUEST_400);
            return e.getMessage();
        }
    }
}
