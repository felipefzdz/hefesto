package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.UserServiceTestModule;
import org.olid16.actions.CreateUser;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;

public class CreateUserStepDefs {
    private String name;
    private User user;
    private String role;

    @Given("^User fills employer data$")
    public void User_fills_employer_data() throws Throwable {
        name = "Bob";
        role = "Employer";
    }

    @When("^the user creates a user$")
    public void the_user_creates_a_user() throws Throwable {
        CreateUser createUser =
                Guice.
                createInjector(new UserServiceTestModule()).
                getInstance(CreateUser.class);
        user = createUser.with(new JsonEntity(JsonObject.readFrom("{\"name\" : \""+ name +"\", " + "\"role\" : \""+ role +"\"}")));
    }

    @Then("^an employer is created$")
    public void an_employer_is_created() throws Throwable {
        assertThat(user.isEmployer()).isTrue();
    }

}
