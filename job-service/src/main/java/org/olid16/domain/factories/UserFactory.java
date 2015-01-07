package org.olid16.domain.factories;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;
import retrofit.RetrofitError;

import java.util.Optional;

public class UserFactory {
    private final UserApi userApi;
    private final UserIdFactory userIdFactory;

    @Inject
    public UserFactory(UserApi userApi, UserIdFactory userIdFactory) {
        this.userApi = userApi;
        this.userIdFactory = userIdFactory;
    }

    public Optional<User> create(JsonEntity jsonEntity) {
        try {
            return Optional.of(userApi.getBy(userIdFactory.create(jsonEntity).id()));
        } catch (RetrofitError e) {
            return Optional.empty();
        }
    }
}
