package infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import infrastructure.clients.InMemoryJobApi;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.repositories.InMemoryJobApplications;
import infrastructure.repositories.InMemoryResumes;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.collections.Resumes;
import org.olid16.infrastructure.circuit_breaker.commands.GetJobByIdCommandFactory;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.apis.UserApi;

public class JobApplicationServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
        install(new FactoryModuleBuilder()
                .build(GetJobByIdCommandFactory.class));
        bind(UserApi.class).to(InMemoryUserApi.class).in(Singleton.class);
        bind(Resumes.class).to(InMemoryResumes.class).in(Singleton.class);
        bind(JobApi.class).to(InMemoryJobApi.class).in(Singleton.class);
        bind(JobApplications.class).to(InMemoryJobApplications.class).in(Singleton.class);
    }
}
