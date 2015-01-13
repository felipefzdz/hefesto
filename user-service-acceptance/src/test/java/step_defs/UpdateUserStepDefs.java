package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.InMemoryUsers;
import infrastructure.dependency_injection.Provider;
import org.olid16.domain.values.UserId;

import static com.google.common.truth.Truth.assertThat;

public class UpdateUserStepDefs {

    public static final String NAME = "Peter";
    private Provider provider = Provider.getSingleton();
    private InMemoryUsers inMemoryUsers = provider.inMemoryUsers();
    private String userId = inMemoryUsers.firstUserId();

    @When("^someone updates that user with different name$")
    public void someone_updates_that_user_with_different_name() throws Throwable {
        provider.updateUser().with(userId, NAME);
    }

    @Then("^the user has the new name$")
    public void the_user_has_the_new_name() throws Throwable {
        assertThat(inMemoryUsers.getNameBy(UserId.create(userId))).isEqualTo(NAME);
    }
}
