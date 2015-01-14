package infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.repositories.InMemoryJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.UserApi;

public class JobServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
        bind(UserApi.class).to(InMemoryUserApi.class).in(Singleton.class);
        bind(Jobs.class).to(InMemoryJobs.class).in(Singleton.class);
    }


}
