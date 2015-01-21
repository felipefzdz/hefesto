package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.JobAdapter;
import org.olid16.infrastructure.rest.entities.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/jobs")
@Api("/jobs")
public class JobResource {

    private final CreateJob createJob;
    private final GetJobs getJobs;
    private final AddJobseekerToJob addJobSeekerToJob;
    private final JobAdapter jobAdapter;

    @Inject
    public JobResource(CreateJob createJob,
                       GetJobs getJobs,
                       AddJobseekerToJob addJobSeekerToJob, 
                       JobAdapter jobAdapter) {
        this.createJob = createJob;
        this.getJobs = getJobs;
        this.addJobSeekerToJob = addJobSeekerToJob;
        this.jobAdapter = jobAdapter;
    }

    @GET
    @Path("/employerId/{employerId}")
    @ApiOperation("Get list of jobs by employer id")
    public Response getByEmployer(@PathParam("employerId") String employerId){
        List<org.olid16.domain.entities.Job> jobs = getJobs.byEmployer(employerId);
        if (jobs.isEmpty()) {
            throw new WebApplicationException(NOT_FOUND_404);
        }
        return Response.ok(jobAdapter.fromDomain(jobs)).build();
    }

    @GET
    @Path("/jobseekerId/{jobseekerId}")
    @ApiOperation("Get list of jobs by jobseeker id")
    public Response getByJobseeker(@PathParam("jobseekerId") String jobseekerId){
        List<org.olid16.domain.entities.Job> jobs = getJobs.byJobseeker(jobseekerId);
        if (jobs.isEmpty()) {
            throw new WebApplicationException(NOT_FOUND_404);
        }
        return Response.ok(jobAdapter.fromDomain(jobs)).build();
    }

    @GET
    @ApiOperation("Get list of all jobs")
    public Response getAll(){
        List<org.olid16.domain.entities.Job> jobs = getJobs.all();
        if (jobs.isEmpty()) {
            throw new WebApplicationException(NOT_FOUND_404);
        }
        return Response.ok(jobAdapter.fromDomain(jobs)).build();
    }

    @POST
    @ApiOperation("Create a job")
    public Response create(Job job){
        try {
            org.olid16.domain.entities.Job createdJob = createJob.with(jobAdapter.toDomain(job));
            return Response.ok(jobAdapter.fromDomain(createdJob)).build();
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }

    @PUT
    @Path("/{jobId}")
    @ApiOperation("Add jobseeker to a job")
    public void addJobseekerToJob(Job job, @PathParam("jobId")String jobId){
        try {
            addJobSeekerToJob.with(job.getUserId(), jobId);
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get job by id")
    public Response getById(@PathParam("id") String id) {
        return getJobs.byId(id).
                map(job -> Response.ok(jobAdapter.fromDomain(job)).build())
                .orElseThrow(() -> new WebApplicationException(NOT_FOUND_404));
    }
}
