package org.olid16.domain.services;

import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;
import retrofit.RetrofitError;

public class UserService {

    private final UserApi userApi;
    private final UserIdFactory userIdFactory;

    @Inject
    public UserService(UserApi userApi, UserIdFactory userIdFactory) {
        this.userApi = userApi;
        this.userIdFactory = userIdFactory;
    }
    
    public boolean isEmployer(JsonEntity jsonEntity){
        try {
            User user = userApi.getBy(userIdFactory.create(jsonEntity).id());
            return UserRole.EMPLOYER.equals(user.userRole());
        } catch (RetrofitError e) {
            return false;
        }
    }
}
