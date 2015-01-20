package org.olid16.infrastructure.clients.apis;

import org.olid16.infrastructure.clients.entities.User;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UserApi {

    @GET("/users/{userId}")
    User getBy(@Path("userId") String userId);
}
