package org.olid16.infrastructure.circuit_breaker.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.yammer.tenacity.core.TenacityCommand;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserApi;

import java.util.Optional;

import static org.olid16.infrastructure.circuit_breaker.JobServiceTenacityPropertyKey.USER_SERVICE;

public class GetUserByIdCommand extends TenacityCommand<Optional<User>> {
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
    protected Optional<User> run() throws Exception {
        return Optional.of(userApi.getBy(userId));
    }

    @Override
    protected Optional<User> getFallback() {
        return Optional.empty();
    }
}
