package org.olid16.domain.factories;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserAdapter;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.RetrofitError;

import java.util.Optional;

public class UserFactory {
    private final UserApi userApi;
    private final UserAdapter userAdapter;

    @Inject
    public UserFactory(UserApi userApi, UserAdapter userAdapter) {
        this.userApi = userApi;
        this.userAdapter = userAdapter;
    }

    public Optional<User> create(UserId userId) {
        try {
            org.olid16.infrastructure.clients.User user = userApi.getBy(userId.id());
            return Optional.of(userAdapter.fromClient(user));
        } catch (RetrofitError e) {
            return Optional.empty();
        }
    }
}
