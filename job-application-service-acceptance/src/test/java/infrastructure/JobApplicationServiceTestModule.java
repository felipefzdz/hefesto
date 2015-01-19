package infrastructure;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.olid16.domain.collections.Resumes;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.UserApi;

public class JobApplicationServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
        bind(UserApi.class).to(InMemoryUserApi.class).in(Singleton.class);
        bind(Resumes.class).to(InMemoryResumes.class).in(Singleton.class);
    }
}
