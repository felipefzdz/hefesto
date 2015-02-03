package step_defs;

import builders.Fixtures;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.JobApplication;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GetJobApplicationsByJobseekerStepDefs {

    private static Provider provider = Provider.getSingleton();

    private List<JobApplication> jobApplications;

    @Before
    public static void beforeScenario(){
        provider.clear();
    }

    @When("^the jobseeker retrieves job applications$")
    public void the_jobseeker_retrieves_job_applications() throws Throwable {
        jobApplications = provider.getJobApplications().byJobseeker("1234");
    }

    @Then("^the jobseeker related job application is there$")
    public void the_jobseeker_related_job_application_is_there() throws Throwable {
        assertThat(jobApplications).isNotEmpty();
    }
}
