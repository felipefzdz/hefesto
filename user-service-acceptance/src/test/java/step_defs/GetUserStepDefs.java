package step_defs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.entities.User;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class GetUserStepDefs {

    private String userId;
    private Optional<User> user;
    private Provider provider = Provider.getSingleton();

    @Given("^A user exists$")
    public void a_users_exists() throws Throwable {
        User user = provider.createUser().with("Bob", "Employer");
        userId = user.id();
    }

    @When("^the user gets a user by id$")
    public void the_user_gets_a_user_by_id() throws Throwable {
        user = provider.getUser().by(userId);
    }

    @Then("^a user is returned$")
    public void a_user_is_returned() throws Throwable {
        assertThat(user.isPresent()).isTrue();
    }




}
