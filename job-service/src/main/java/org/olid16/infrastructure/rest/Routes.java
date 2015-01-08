package org.olid16.infrastructure.rest;

import com.google.inject.Inject;

import static spark.Spark.post;
import static spark.Spark.get;
import static spark.SparkBase.port;

public class Routes {

    private final JobController jobController;

    @Inject
    public Routes(JobController jobController) {
        this.jobController = jobController;
    }

    public void initialise() {
        port(8081);
        post("/job", (req, res) -> jobController.create(req, res));
        get("/jobs/:employerId", (req, res) -> jobController.getByEmployer(req, res));
    }
}
