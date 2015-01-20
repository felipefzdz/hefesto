package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.ResumeId;
import org.olid16.infrastructure.clients.entities.User;

import static builders.ResumeBuilder.aResume;
import static com.google.common.truth.Truth.assertThat;

public class CreateResumeStepDefs {

    private static Provider provider = Provider.getSingleton();
    private Resume resume;

    @Given("^A jobseeker exists$")
    public void A_jobseeker_exists() throws Throwable {
        provider.userApi().add(new User("1234", "Bob", "jobseeker"));
    }

    @When("^the jobseeker creates a resume$")
    public void the_jobseeker_creates_a_resume() throws Throwable {
        resume = provider.createResume().with(aResume().w(ResumeId.create("")).build());
    }

    @Then("^the resume is created$")
    public void the_resume_is_created() throws Throwable {
        assertThat(resume.id()).isNotNull();
    }


}
