package step_defs;

import com.eclipsesource.json.JsonArray;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.*;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static infrastructure.Fixtures.JOBSEEKER_ID;
import static infrastructure.Fixtures.TITLES;
import static infrastructure.Fixtures.USER_ID;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.JOBSEEKER;

public class GetAllJobsStepDefs {

    private static Provider provider = Provider.getSingleton();

    private List<Job> jobs;

    @Before
    public static void beforeScenario(){
        provider.clear();
    }

    @Given("^User creates several jobs$")
    public void User_creates_several_jobs() throws Throwable {
        Jobs inMemoryJobs = provider.inMemoryJobs();
        for(String title: TITLES){
            inMemoryJobs.add(Job.createJob(JobId.create(title), User.create(create("Bob"), JOBSEEKER, UserId.create(JOBSEEKER_ID)), Title.create(title), JobType.ATS));
        }
    }

    @When("^User get all jobs$")
    public void User_get_all_jobs() throws Throwable {
        jobs = provider.getJobs().all();
    }

    @Then("^all jobs are retrieved$")
    public void all_jobs_are_retrieved() throws Throwable {
        assertThat(jobs.size()).is(3);
    }
}
