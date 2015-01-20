package org.olid16.infrastructure.rest.resources;

import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.olid16.actions.CreateResume;
import org.olid16.infrastructure.rest.adapters.ResumeAdapter;
import org.olid16.infrastructure.rest.entities.Resume;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/resumes")
@Api("/resumes")
public class ResumeResource {

    private final CreateResume createResume;
    private final ResumeAdapter resumeAdapter;

    @Inject
    public ResumeResource(CreateResume createResume, ResumeAdapter resumeAdapter) {
        this.createResume = createResume;
        this.resumeAdapter = resumeAdapter;
    }

    @POST
    @ApiOperation("Create a resume")
    public Response create(Resume resume) {
        org.olid16.domain.entities.Resume createdResume = createResume.with(resumeAdapter.toDomain(resume));
        return Response.ok(resumeAdapter.fromDomain(createdResume)).build();
    }
}
