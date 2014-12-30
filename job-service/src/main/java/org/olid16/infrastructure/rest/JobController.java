package org.olid16.infrastructure.rest;

import com.google.inject.Inject;
import org.olid16.actions.CreateJob;
import org.olid16.infrastructure.exceptions.DomainException;
import spark.Request;
import spark.Response;

import static com.eclipsesource.json.JsonObject.readFrom;
import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

public class JobController {
    private final CreateJob createJob;

    @Inject
    public JobController(CreateJob createJob) {
        this.createJob = createJob;
    }

    public String create(Request req, Response res) {
        JsonEntity jsonEntity = new JsonEntity(readFrom(req.body()));
        try{
            return createJob.with(jsonEntity).id();
        } catch (DomainException e) {
            res.status(BAD_REQUEST_400);
            return e.getMessage();
        }
    }
}
