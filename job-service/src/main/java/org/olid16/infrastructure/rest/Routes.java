package org.olid16.infrastructure.rest;

import static spark.Spark.post;

public class Routes {
    private JobController jobController;

    public void initialise() {
        post("/job", (req, res) -> jobController.create(req, res));
    }
}
