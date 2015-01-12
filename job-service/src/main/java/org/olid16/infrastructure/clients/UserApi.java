package org.olid16.infrastructure.clients;

import org.olid16.domain.values.User;
import retrofit.http.GET;
import retrofit.http.Path;

public interface UserApi {
    
    @GET("/users/{userId}")
    User getBy(@Path("userId") String userId);
}
