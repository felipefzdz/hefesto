package infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserAdapter;
import org.olid16.infrastructure.clients.UserClient;
import us.monoid.web.Resty;

import java.util.HashMap;
import java.util.Map;

import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;

public class InMemoryUserClient extends UserClient{

    private Map<UserId, User> users = new HashMap<>();

    @Inject
    public InMemoryUserClient(Resty resty, UserAdapter userAdapter) {
        super(resty, userAdapter);
        users.put(UserId.create("1234"), User.create(create("Bob"), EMPLOYER));
    }

    @Override
    public User getBy(UserId userId) {
        return users.get(userId);
    }

}
