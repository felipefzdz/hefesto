package org.olid16.infrastructure.rest;

import com.google.inject.Inject;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.infrastructure.exceptions.DomainException;
import spark.Request;
import spark.Response;

import java.util.Optional;

import static com.eclipsesource.json.JsonObject.readFrom;
import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;

public class JobController {
    private static final String EMPTY = "";
    private final CreateJob createJob;
    private final GetJobs getJobs;

    @Inject
    public JobController(CreateJob createJob, GetJobs getJobs) {
        this.createJob = createJob;
        this.getJobs = getJobs;
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

    public String getByEmployer(Request req, Response res) {
        Optional<String> jobs = getJobs.byEmployer(req.params("employerId"));
        if(jobs.isPresent()){
            return jobs.get();
        }
        res.status(NOT_FOUND_404);
        return EMPTY;
    }
}
