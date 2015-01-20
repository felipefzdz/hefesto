package org.olid16.infrastructure.circuit_breaker.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.yammer.tenacity.core.TenacityCommand;
import org.olid16.infrastructure.clients.entities.User;
import org.olid16.infrastructure.clients.apis.UserApi;

import static org.olid16.infrastructure.circuit_breaker.JobApplicationServiceTenacityPropertyKey.USER_SERVICE;

public class GetUserByIdCommand extends TenacityCommand<User> {
    private final UserApi userApi;
    private final String userId;

    @Inject
    public GetUserByIdCommand(UserApi userApi, 
                              @Assisted String userId) {
        super(USER_SERVICE);
        this.userApi = userApi;
        this.userId = userId;
    }

    @Override
    protected User run() throws Exception {
        return userApi.getBy(userId);
    }

    @Override
    protected User getFallback() {
        return new User("", "", "");
    }
}
