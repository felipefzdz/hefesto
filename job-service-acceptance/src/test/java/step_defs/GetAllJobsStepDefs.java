package step_defs;

import com.eclipsesource.json.JsonArray;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.Fixtures;
import infrastructure.dependency_injection.JobServiceTestModule;
import infrastructure.dependency_injection.Provider;
import org.olid16.actions.GetJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.UserId;

import static com.google.common.truth.Truth.assertThat;
import static infrastructure.Fixtures.*;

public class GetAllJobsStepDefs {

    private String jobs;
    private Provider provider = new Provider();

    @Given("^User creates several jobs$")
    public void User_creates_several_jobs() throws Throwable {
        Jobs inMemoryJobs = provider.inMemoryJobs();
        for(String title: TITLES){
            inMemoryJobs.add(new Job(JobId.create(title), UserId.create(USER_ID), Title.create(title)));
        }
    }

    @When("^User get all jobs$")
    public void User_get_all_jobs() throws Throwable {
        jobs = provider.getJobs().all().get();
    }

    @Then("^all jobs are retrieved$")
    public void all_jobs_are_retrieved() throws Throwable {
        assertThat(JsonArray.readFrom(jobs).size()).is(3);
    }
}
