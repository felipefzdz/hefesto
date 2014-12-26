package org.olid16.infrastructure.rest;

import com.google.inject.Inject;
import org.olid16.infrastructure.rest.controllers.EmployerController;

import static spark.Spark.post;

public class Routes {
    private final EmployerController employerController;

    @Inject
    public Routes(EmployerController employerController) {
        this.employerController = employerController;
    }

    public void initialise() {
        post("/employer", (req, res) -> employerController.create(req, res));
    }
}
