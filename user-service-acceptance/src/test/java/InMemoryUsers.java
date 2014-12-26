import org.olid16.domain.User;
import org.olid16.domain.UserId;
import org.olid16.domain.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryUsers implements Users {
    private final Map<UserId, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        users.put(UserId.create(user.id()), user);
    }

    @Override
    public UserId nextId() {
        return UserId.create(UUID.randomUUID().toString());
    }
}
