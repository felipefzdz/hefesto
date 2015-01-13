package infrastructure;

import org.olid16.domain.collections.Users;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;

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

    @Override
    public void update(User user) {
        users.replace(UserId.create(user.id()), user);
    }

    public String getNameBy(UserId userId){
        return users.get(userId).name();
        
    }
    
    public String firstUserId(){
        return ((UserId) users.keySet().toArray()[0]).id();

    }
}
