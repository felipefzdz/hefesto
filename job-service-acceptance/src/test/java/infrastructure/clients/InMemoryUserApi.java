package infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.http.Path;

import java.util.HashMap;
import java.util.Map;

import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;

public class InMemoryUserApi implements UserApi {

    private Map<UserId, User> users = new HashMap<>();

    @Inject
    public InMemoryUserApi() {
        users.put(UserId.create("1234"), User.create(create("Bob"), EMPLOYER));
    }

    @Override
    public User getBy(@Path("userId") String userId) {
        return users.get(UserId.create(userId));
    }
}
