package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.repositories.InMemoryJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserApi;

public class JobServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserApi.class).to(InMemoryUserApi.class).in(Singleton.class);
        bind(Jobs.class).to(InMemoryJobs.class).in(Singleton.class);
    }


}
