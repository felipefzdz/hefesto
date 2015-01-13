package step_defs;

import com.eclipsesource.json.JsonArray;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;

import static com.google.common.truth.Truth.assertThat;
import static infrastructure.Fixtures.JOBSEEKER_ID;
import static infrastructure.Fixtures.TITLES;
import static infrastructure.Fixtures.USER_ID;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.EMPLOYER;
import static org.olid16.domain.values.UserRole.JOBSEEKER;

public class GetJobsByEmployerStepDefs {

    private static Provider provider = Provider.getSingleton();
    
    

    private String jobs;
    
    @Before
    public static void beforeScenario(){
        provider.clear();
    }
    

    @And("^Employer creates several jobs$")
    public void Employer_creates_several_jobs() throws Throwable {
        Jobs inMemoryJobs = provider.inMemoryJobs();
        for(String title: TITLES){
            inMemoryJobs.add(Job.createJob(JobId.create(title), User.create(create("Bob"), EMPLOYER, USER_ID), Title.create(title)));
        }
    }

    @When("^the employer gets the jobs$")
    public void the_employer_gets_the_jobs() throws Throwable {
        jobs = provider.getJobs().byEmployer(USER_ID).get();
    }

    @Then("^jobs are retrieved$")
    public void jobs_are_retrieved() throws Throwable {
        assertThat(JsonArray.readFrom(jobs).size()).is(3);
    }


}
