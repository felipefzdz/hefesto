package step_defs;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import infrastructure.JobServiceTestModule;
import infrastructure.clients.InMemoryUserApi;
import infrastructure.repositories.InMemoryJobs;
import org.olid16.actions.AddJobseekerToJob;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.entities.Jobseekers;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.domain.values.Person.create;
import static org.olid16.domain.values.UserRole.JOBSEEKER;

public class AddJobseekerToJobStepDefs {

    private Injector injector = Guice.createInjector(new JobServiceTestModule());
    private String employerId = "employerId";
    private String jobseekerId = "jobseekerId";
    private String jobId = "jobId";
    private InMemoryJobs inMemoryJobs = (InMemoryJobs)injector.getInstance(Jobs.class);


    @Given("^A jobseeker exists$")
    public void a_jobseeker_exists() throws Throwable {
        InMemoryUserApi userApi = (InMemoryUserApi)injector.getInstance(UserApi.class);
        userApi.add(User.create(create("Bob"), JOBSEEKER, jobseekerId));
    }

    @And("^a job exists$")
    public void a_job_exists() throws Throwable {
        inMemoryJobs.add(new Job(JobId.create(jobId), UserId.create(employerId), Title.create("Developer")));
    }

    @When("^the jobseeker save the job$")
    public void the_jobseeker_save_the_job() throws Throwable {
        AddJobseekerToJob addJobseekerToJob = injector.getInstance(AddJobseekerToJob.class);
        JsonEntity jsonEntity = new JsonEntity(JsonObject.readFrom("{\"userId\" : \"" + jobseekerId + "\"}"));
        addJobseekerToJob.with(jsonEntity, jobId);
    }

    @Then("^the jobseeker get added into that job$")
    public void the_jobseeker_get_added_into_that_job() throws Throwable {
        Job job = inMemoryJobs.byId(jobId);
        assertThat(job.interestedJobseekers().contains(UserId.create(jobseekerId))).isTrue();
    }


}
