package org.olid16.infrastructure.rest;

import com.google.inject.Inject;
import org.olid16.infrastructure.rest.controllers.UserController;

import static spark.Spark.post;

public class Routes {
    private final UserController userController;

    @Inject
    public Routes(UserController userController) {
        this.userController = userController;
    }

    public void initialise() {
        post("/user", (req, res) -> userController.create(req, res));
    }
}
