package infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.olid16.actions.CreateResume;
import org.olid16.infrastructure.clients.UserApi;

public class Provider {

    private static Provider instance = new Provider();

    private Provider() {
    }


    public static Provider getSingleton() {
        return instance;
    }

    private Injector injector = Guice.createInjector(new JobApplicationServiceTestModule());

    public InMemoryUserApi userApi() {
        return (InMemoryUserApi)injector.getInstance(UserApi.class);
    }


    public CreateResume createResume() {
        return injector.getInstance(CreateResume.class);
    }
}
