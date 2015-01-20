package org.olid16.infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommand;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.adapters.UserAdapter;
import retrofit.RetrofitError;

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
        try {
            GetUserByIdCommand getUserByIdCommand = factory.create(userId.id());
            org.olid16.infrastructure.clients.entities.User user = getUserByIdCommand.execute();
            return Optional.of(userAdapter.fromClient(user));
        } catch (RetrofitError e) {
            return Optional.empty();
        }
    }
}
