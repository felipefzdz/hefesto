import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.olid16.actions.CreateEmployer;
import org.olid16.domain.User;

import static com.google.common.truth.Truth.assertThat;

public class CreateEmployerStepDefs {
    private String name;
    private User user;

    @Given("^User fills employer data$")
    public void User_fills_employer_data() throws Throwable {
        name = "Bob";
    }

    @When("^the user create an employer$")
    public void the_user_create_an_employer() throws Throwable {
       user = new CreateEmployer(null).with(name);
    }

    @Then("^an employer is created$")
    public void an_employer_is_created() throws Throwable {
        assertThat(user.isEmployer()).isTrue();
    }


}
