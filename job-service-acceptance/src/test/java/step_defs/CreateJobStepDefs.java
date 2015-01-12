package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.Fixtures;
import infrastructure.dependency_injection.JobServiceTestModule;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.dependency_injection.Provider;
import org.olid16.actions.CreateJob;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.User;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;
import static infrastructure.Fixtures.*;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;

public class CreateJobStepDefs {
    private String title;
    private Job job;
    private Provider provider = new Provider();
    
    @Given("^An employer exists$")
    public void An_employer_exists() throws Throwable{
        provider.userApi().add(User.create(create("Bob"), EMPLOYER, USER_ID));
        
    }

    @And("^Employer fills job data$")
    public void Employer_fills_job_data() throws Throwable {
        title = "Developer at X";
    }

    @When("^the employer creates a job$")
    public void the_employer_creates_a_job() throws Throwable {
        job = provider.createJob().with(new JsonEntity(JsonObject.readFrom("{\"title\" : \"" + title + "\", " + "\"userId\" : \"" + USER_ID + "\"}")));
    }


    @Then("^a job is created$")
    public void a_job_is_created() throws Throwable {
        assertThat(job).isNotNull();
    }


    
}
