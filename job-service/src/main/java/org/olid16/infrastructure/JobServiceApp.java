package org.olid16.infrastructure;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.olid16.infrastructure.dependency_injection.JobServiceModule;
import org.olid16.infrastructure.events.UserEventConsumer;

public class JobServiceApp extends Application<JobServiceConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

    public static void main(String[] args) throws Exception {
        new JobServiceApp().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        bootstrap.addBundle(guice());
        swaggerDropwizard.onInitialize(bootstrap);
    }

    private GuiceBundle<JobServiceConfiguration> guice() {
        return GuiceBundle.<JobServiceConfiguration>newBuilder()
                .addModule(new JobServiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(JobServiceConfiguration.class)
                .build();
    }

    @Override
    public void run(JobServiceConfiguration configuration, Environment environment) throws Exception {
        swaggerDropwizard.onRun(configuration, environment, "localhost");
    }
}
