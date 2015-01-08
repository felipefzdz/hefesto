package org.olid16.domain.factories;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.RetrofitError;

import java.util.Optional;

public class UserFactory {
    private final UserApi userApi;

    @Inject
    public UserFactory(UserApi userApi) {
        this.userApi = userApi;
    }

    public Optional<User> create(UserId userId) {
        try {
            return Optional.of(userApi.getBy(userId.id()));
        } catch (RetrofitError e) {
            return Optional.empty();
        }
    }
}
