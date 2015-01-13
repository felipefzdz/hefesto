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
import static infrastructure.Fixtures.*;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.JOBSEEKER;

public class AddJobseekerToJobStepDefs{

    private static Provider provider = Provider.getSingleton();

    @Before
    public static void beforeScenario(){
        provider.clear();
    }

    @Given("^A jobseeker exists$")
    public void a_jobseeker_exists() throws Throwable {
        provider.userApi().add(aUser());
    }

    @And("^a job exists$")
    public void a_job_exists() throws Throwable {
        provider.inMemoryJobs().add(Job.createJob(JobId.create(JOB_ID), aUser(), Title.create("Developer")));
    }

    @When("^the jobseeker save the job$")
    public void the_jobseeker_save_the_job() throws Throwable {
        provider.addJobseekerToJob().with(JOBSEEKER_ID, JOB_ID);
    }

    @Then("^the jobseeker get added into that job$")
    public void the_jobseeker_get_added_into_that_job() throws Throwable {
        Job job = provider.inMemoryJobs().byId(JOB_ID);
        assertThat(job.interestedJobseekers().contains(UserId.create(JOBSEEKER_ID))).isTrue();
    }

    private User aUser() {
        return User.create(create("Bob"), JOBSEEKER, JOBSEEKER_ID);
    }

}
