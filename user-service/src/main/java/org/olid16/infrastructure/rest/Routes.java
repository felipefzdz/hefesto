package org.olid16.infrastructure.rest;

import com.google.inject.Inject;
import org.olid16.infrastructure.rest.controllers.UserController;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;
import static spark.SparkBase.setPort;

public class Routes {
    private final UserController userController;

    @Inject
    public Routes(UserController userController) {
        this.userController = userController;
    }

    public void initialise() {
        port(8080);
        post("/user", (req, res) -> userController.create(req, res));
        get("/user/:userId", (req, res) -> userController.get(req, res));
    }
}
