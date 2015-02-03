package infrastructure.dependency_injection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import infrastructure.clients.InMemoryJobApi;
import infrastructure.clients.InMemoryUserApi;
import org.olid16.actions.CreateJobApplication;
import org.olid16.actions.CreateResume;
import org.olid16.actions.GetJobApplications;
import org.olid16.infrastructure.clients.JobClient;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.apis.UserApi;

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

    public InMemoryJobApi jobApi() {
        return (InMemoryJobApi)injector.getInstance(JobApi.class);
    }

    public CreateJobApplication createJobApplication() {
        return injector.getInstance(CreateJobApplication.class);
    }

    public JobClient createJobClient() {
        return injector.getInstance(JobClient.class);
    }

    public UserClient createUserClient() {
        return injector.getInstance(UserClient.class);
    }

    public void clear() {
        userApi().clear();
        jobApi().clear();
    }

    public GetJobApplications getJobApplications() {
        return injector.getInstance(GetJobApplications.class);
    }
}
