package infrastructure.dependency_injection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.repositories.InMemoryJobs;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.actions.CreateJob;
import org.olid16.actions.GetJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserApi;

public class Provider {

    private Injector injector = Guice.createInjector(new JobServiceTestModule());

    public GetJobs getJobs() {
        return injector.getInstance(GetJobs.class);
    }

    public CreateJob createJob() {
        return injector.getInstance(CreateJob.class);
    }

    public AddJobseekerToJob addJobseekerToJob() {
        return injector.getInstance(AddJobseekerToJob.class);
    }

    public InMemoryUserApi userApi() {
        return (InMemoryUserApi)injector.getInstance(UserApi.class);
    }

    public InMemoryJobs inMemoryJobs() {
        return (InMemoryJobs)injector.getInstance(Jobs.class);
    }
}
