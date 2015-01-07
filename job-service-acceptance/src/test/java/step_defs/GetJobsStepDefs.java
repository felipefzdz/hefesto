package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.JobServiceTestModule;
import infrastructure.clients.InMemoryUserApi;
import org.olid16.actions.GetJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;

public class GetJobsStepDefs {

    private String[] titles = {"1", "2", "3"};
    private String userId;
    private Injector injector = Guice.
            createInjector(new JobServiceTestModule());
    private List<Job> jobs;

    @Given("^An employer exists when get jobs$")
    public void An_employer_exists_when_get_jobs() throws Throwable{
        userId = "1234";
        InMemoryUserApi userApi = (InMemoryUserApi)injector.getInstance(UserApi.class);
        userApi.add(User.create(create("Bob"), EMPLOYER, userId));

    }

    @And("^Employer creates several jobs$")
    public void Employer_creates_several_jobs() throws Throwable {
        Jobs inMemoryJobs = injector.getInstance(Jobs.class);
        for(String title: titles){
            inMemoryJobs.add(new Job(JobId.create(title), UserId.create(userId), Title.create(title)));
        }
    }

    @When("^the employer gets the jobs$")
    public void the_employer_gets_the_jobs() throws Throwable {
        GetJobs getJobs = injector.getInstance(GetJobs.class);
        jobs = getJobs.with(new JsonEntity(JsonObject.readFrom("{\"userId\" : \"" + userId + "\"}")));
    }

    @Then("^jobs are retrieved$")
    public void jobs_are_retrieved() throws Throwable {
        assertThat(jobs).hasSize(3);
    }


}
