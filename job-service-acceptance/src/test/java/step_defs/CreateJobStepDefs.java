package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.JobServiceTestModule;
import org.olid16.actions.CreateJob;
import org.olid16.domain.entities.Job;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;

public class CreateJobStepDefs {
    private String title;
    private String userId;
    private Job job;

    @Given("^Employer fills job data$")
    public void Employer_fills_job_data() throws Throwable {
        title = "Developer at X";
        userId = "1234";
    }

    @When("^the employer creates a job$")
    public void the_employer_creates_a_job() throws Throwable {
        CreateJob createJob =
                    Guice.
                    createInjector(new JobServiceTestModule()).
                    getInstance(CreateJob.class);
        job = createJob.with(new JsonEntity(JsonObject.readFrom("{\"title\" : \"" + title + "\", " + "\"userId\" : \"" + userId + "\"}")));
    }

    @Then("^a job is created$")
    public void a_job_is_created() throws Throwable {
        assertThat(job).isNotNull();
    }


}
