package step_defs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.Fixtures;
import infrastructure.dependency_injection.Provider;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class GetJobsByJobseeker  {

    private static Provider provider = Provider.getSingleton();
    
    private Optional<String> jobs;

    @Before
    public static void beforeScenario(){
        provider.clear();
    }

    @When("^the jobseeker retrieves jobs$")
    public void the_jobseeker_retrieves_jobs() throws Throwable {
        jobs = provider.getJobs().byJobseeker(Fixtures.JOBSEEKER_ID);
    }

    @Then("^the jobseeker related job is there$")
    public void the_jobseeker_related_job_is_there() throws Throwable {
        assertThat(jobs.isPresent()).isTrue();
    }
}
