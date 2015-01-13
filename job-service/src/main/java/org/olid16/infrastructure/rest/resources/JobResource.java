package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.infrastructure.exceptions.DomainException;
import org.olid16.infrastructure.rest.entities.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/jobs")
public class JobResource {

    private final CreateJob createJob;
    private final GetJobs getJobs;
    private final AddJobseekerToJob addJobSeekerToJob;

    @Inject
    public JobResource(CreateJob createJob, GetJobs getJobs, AddJobseekerToJob addJobSeekerToJob) {
        this.createJob = createJob;
        this.getJobs = getJobs;
        this.addJobSeekerToJob = addJobSeekerToJob;
    }

    @GET
    @Path("employerId/{employerId}")
    public String getByEmployer(@PathParam("employerId") String employerId){
        Optional<String> job = getJobs.byEmployer(employerId);
        if (job.isPresent()) {
            return job.get();
        }
        throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
    }

    @GET
    @Path("jobseekerId/{jobseekerId}")
    public String getByJobseeker(@PathParam("jobseekerId") String jobseekerId){
        Optional<String> job = getJobs.byJobseeker(jobseekerId);
        if (job.isPresent()) {
            return job.get();
        }
        throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
    }

    @GET
    public String getAll(){
        Optional<String> job = getJobs.all();
        if (job.isPresent()) {
            return job.get();
        }
        throw new WebApplicationException(HttpStatus.NOT_FOUND_404);
    }

    @POST
    public String create(Job job){
        try {
            return createJob.with(job.getUserId(), job.getTitle()).id();
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }

    @PUT
    @Path("{jobId}")
    public void addJobseekerToJob(Job job, @PathParam("jobId")String jobId){
        try {
            addJobSeekerToJob.with(job.getUserId(), jobId);
        } catch (DomainException e) {
            throw new WebApplicationException(e, BAD_REQUEST_400);
        }
    }
}
