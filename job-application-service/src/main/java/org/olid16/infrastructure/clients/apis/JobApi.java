package org.olid16.infrastructure.clients.apis;

import org.olid16.infrastructure.clients.entities.Job;
import org.olid16.infrastructure.clients.entities.User;
import retrofit.http.GET;
import retrofit.http.Path;

public interface JobApi {

    @GET("/jobs/{jobId}")
    Job getBy(@Path("jobId") String jobId);
}
