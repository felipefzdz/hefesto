package org.olid16.domain.services;

import com.google.inject.Inject;
import org.olid16.domain.factories.UserIdFactory;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserRole;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.rest.JsonEntity;

public class UserService {

    private final UserClient userClient;
    private final UserIdFactory userIdFactory;

    @Inject
    public UserService(UserClient userClient, UserIdFactory userIdFactory) {
        this.userClient = userClient;
        this.userIdFactory = userIdFactory;
    }
    
    public boolean isEmployer(JsonEntity jsonEntity){
        User user = userClient.getBy(userIdFactory.create(jsonEntity));
        return UserRole.EMPLOYER.equals(user.userRole());
        
    }
}
