package step_defs;

import com.eclipsesource.json.JsonObject;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.*;

import static com.google.common.truth.Truth.assertThat;
import static infrastructure.Fixtures.USER_ID;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;

public class CreateJobStepDefs {

    private static Provider provider = Provider.getSingleton();
    
    private String title;
    private Job job;

    @Before
    public static void beforeScenario(){
        provider.clear();
    }

    
    @Given("^An employer exists$")
    public void An_employer_exists() throws Throwable{
        provider.userApi().add(new org.olid16.infrastructure.clients.User("1234", "Bob", "employer"));
        
    }

    @And("^Employer fills job data$")
    public void Employer_fills_job_data() throws Throwable {
        title = "Developer at X";
    }

    @When("^the employer creates a job$")
    public void the_employer_creates_a_job() throws Throwable {
        Job jobFromClient = Job.createJob(null, User.create(Person.create(""), UserRole.UNKNOWN, UserId.create(USER_ID)), Title.create(title), JobType.ATS);
        job = provider.createJob().with(jobFromClient);
    }


    @Then("^a job is created$")
    public void a_job_is_created() throws Throwable {
        assertThat(job).isNotNull();
    }


    
}
