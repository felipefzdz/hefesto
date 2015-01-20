package step_defs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.JobApplication;
import org.olid16.infrastructure.clients.entities.Job;

import static builders.JobApplicationBuilder.aJobApplication;
import static com.google.common.truth.Truth.assertThat;

public class CreateJobApplicationStepDefs {

    private static Provider provider = Provider.getSingleton();
    private JobApplication jobApplication;

    @And("^a job exists$")
    public void a_job_exists() throws Throwable {
        provider.jobApi().add(new Job("1234", "title"));
    }

    @When("^the jobseeker apply to the job$")
    public void the_jobseeker_apply_to_the_job() throws Throwable {
        jobApplication = provider.createJobApplication().with(aJobApplication().build());
    }

    @Then("^a job application is created$")
    public void a_job_application_is_created() throws Throwable {
        assertThat(jobApplication.id()).isNotEmpty();
    }
}
