package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.UserServiceTestModule;
import org.olid16.actions.CreateUser;
import org.olid16.actions.GetUser;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.rest.JsonEntity;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;

public class GetUserStepDefs {

    private String userId;
    private Optional<User> user;
    private Injector injector = Guice.createInjector(new UserServiceTestModule());

    @Given("^A user exists$")
    public void a_users_exists() throws Throwable {
        User user = createUser().
                with(new JsonEntity(JsonObject.readFrom("{\"name\" : \"Bob\", " + "\"role\" : \"Employer\"}")));
        userId = user.id();
    }

    @When("^the user gets a user by id$")
    public void the_user_gets_a_user_by_id() throws Throwable {
        user = getUser().by(new JsonEntity(JsonObject.readFrom("{\"userId\" : \"" + userId + "\"}")));
    }

    @Then("^a user is returned$")
    public void a_user_is_returned() throws Throwable {
        assertThat(user.isPresent()).isTrue();
    }

    private CreateUser createUser() {
        return injector.getInstance(CreateUser.class);
    }

    private GetUser getUser() {
        return injector.getInstance(GetUser.class);
    }


}
