package org.olid16.infrastructure.rest.controllers;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.JsonEntity;
import spark.Request;
import spark.Response;

import java.util.Optional;

import static com.eclipsesource.json.JsonObject.readFrom;
import static org.eclipse.jetty.http.HttpStatus.*;
import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

public class UserController {
    private static final String EMPTY_JSON = "{}";
    private final CreateUser createUser;
    private final GetUser getUser;

    @Inject
    public UserController(CreateUser createUser, GetUser getUser) {
        this.createUser = createUser;
        this.getUser = getUser;
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

    public String get(Request req, Response res) {
        Optional<String> user = getUser.by(req.params(":userId"));
        if (user.isPresent()) {
            return user.get();
        }
        res.status(NOT_FOUND_404);
        return EMPTY_JSON;
    }
}
