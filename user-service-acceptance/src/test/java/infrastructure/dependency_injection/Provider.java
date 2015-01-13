package infrastructure.dependency_injection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import infrastructure.InMemoryUsers;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.actions.UpdateUser;
import org.olid16.domain.collections.Users;

public class Provider {

    private static Provider instance = new Provider();
    private Provider() {
    }

    public static Provider getSingleton() {
        return instance;
    }

    private Injector injector = Guice.createInjector(new UserServiceTestModule());

    public CreateUser createUser() {
        return injector.getInstance(CreateUser.class);
    }

    public GetUser getUser() {
        return injector.getInstance(GetUser.class);
    }

    public InMemoryUsers inMemoryUsers() {
        return (InMemoryUsers)injector.getInstance(Users.class);
    }

    public UpdateUser updateUser() {
        return injector.getInstance(UpdateUser.class);
    }
}
