package infrastructure;

import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.User;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.http.Path;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserApi implements UserApi {

    private Map<UserId, User> users = new HashMap<>();

    public void add(User user){
        users.put(UserId.create(user.getId()), user);

    }

    @Override
    public User getBy(@Path("userId") String userId) {
        return users.get(UserId.create(userId));
    }

    public void clear(){
        users.clear();

    }
}

