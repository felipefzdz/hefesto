package org.olid16.infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommand;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.adapters.UserAdapter;

import java.util.Optional;

public class UserClient {
    private final UserAdapter userAdapter;
    private final GetUserByIdCommandFactory factory;

    @Inject
    public UserClient(UserAdapter userAdapter,
                      GetUserByIdCommandFactory factory) {
        this.userAdapter = userAdapter;
        this.factory = factory;
    }

    public Optional<User> create(UserId userId) {
        GetUserByIdCommand getUserByIdCommand = factory.create(userId.id());
        return getUserByIdCommand.execute().
                map(user -> Optional.of(userAdapter.fromClient(user)))
                .orElse(Optional.empty());
    }
}
