package step_defs;

import com.eclipsesource.json.JsonArray;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.JobServiceTestModule;
import org.olid16.actions.GetJobs;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.UserId;

import static com.google.common.truth.Truth.assertThat;

public class GetAllJobsStepDefs {

    private String userId = "1234";
    private String[] titles = {"1", "2", "3"};
    private Injector injector = Guice.createInjector(new JobServiceTestModule());
    private String jobs;

    @Given("^User creates several jobs$")
    public void User_creates_several_jobs() throws Throwable {
        Jobs inMemoryJobs = injector.getInstance(Jobs.class);
        for(String title: titles){
            inMemoryJobs.add(new Job(JobId.create(title), UserId.create(userId), Title.create(title)));
        }
    }

    @When("^User get all jobs$")
    public void User_get_all_jobs() throws Throwable {
        GetJobs getJobs = injector.getInstance(GetJobs.class);
        jobs = getJobs.all().get();
    }

    @Then("^all jobs are retrieved$")
    public void all_jobs_are_retrieved() throws Throwable {
        assertThat(JsonArray.readFrom(jobs).size()).is(3);
    }
}
