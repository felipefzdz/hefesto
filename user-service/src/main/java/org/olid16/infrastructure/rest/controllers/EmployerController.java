package org.olid16.infrastructure.rest.controllers;

import com.google.inject.Inject;
import org.olid16.actions.CreateEmployer;
import org.olid16.domain.User;
import org.olid16.domain.UserJson;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;

public class EmployerController {
    private final CreateEmployer createEmployer;

    @Inject
    public EmployerController(CreateEmployer createEmployer) {
        this.createEmployer = createEmployer;
    }

    public String create(Request req, Response res) {
        UserJson userJson = new UserJson(readFrom(req.body()));
        User user = createEmployer.with(userJson);
        return user.id();
    }
}
