package infrastructure;

import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;
import org.olid16.domain.collections.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public Optional<User> by(UserId userId) {
        return users.containsKey(userId) ? Optional.of(users.get(userId)) : Optional.empty();
    }
}
