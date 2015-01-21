package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.olid16.actions.CreateJobApplication;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.adapters.JobApplicationAdapter;
import org.olid16.infrastructure.rest.entities.JobApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/jobApplications")
@Api("/jobApplications")
public class JobApplicationResource {
    
    private final CreateJobApplication createJobApplication;
    private final JobApplicationAdapter jobApplicationAdapter;

    @Inject
    public JobApplicationResource(CreateJobApplication createJobApplication, JobApplicationAdapter jobApplicationAdapter) {
        this.createJobApplication = createJobApplication;
        this.jobApplicationAdapter = jobApplicationAdapter;
    }


    @POST
    @ApiOperation("Apply to a job")
    public Response create(JobApplication jobApplication) {
        try {
            org.olid16.domain.entities.JobApplication createdJobApplication = createJobApplication.with(jobApplicationAdapter.toDomain(jobApplication));
            return Response.ok(jobApplicationAdapter.fromDomain(createdJobApplication)).build();
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }
}
