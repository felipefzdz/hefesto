package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.User;

import static com.google.common.truth.Truth.assertThat;

public class CreateUserStepDefs {
    private String name;
    private User user;
    private String role;
    
    private Provider provider = Provider.getSingleton();

    @Given("^User fills employer data$")
    public void User_fills_employer_data() throws Throwable {
        name = "Bob";
        role = "Employer";
    }

    @When("^the user creates a user$")
    public void the_user_creates_a_user() throws Throwable {
        user = provider.createUser().with(name, role);
    }

    @Then("^an employer is created$")
    public void an_employer_is_created() throws Throwable {
        assertThat(user.isEmployer()).isTrue();
    }

    
}
