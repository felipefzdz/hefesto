package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import infrastructure.clients.InMemoryUserClient;
import infrastructure.repositories.InMemoryJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserClient;
import us.monoid.web.Resty;

public class JobServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserClient.class).to(InMemoryUserClient.class).in(Singleton.class);
        bind(Jobs.class).to(InMemoryJobs.class).in(Singleton.class);
    }

    @Provides Resty resty(){
        return new Resty();
    }
        

}
