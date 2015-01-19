package org.olid16.infrastructure;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import com.yammer.tenacity.core.bundle.TenacityConfiguredBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.olid16.infrastructure.circuit_breaker.JobApplicationServiceTenacityBundleConfigurationFactory;
import org.olid16.infrastructure.dependency_injection.JobApplicationServiceModule;

public class JobApplicationServiceApp extends Application<JobApplicationServiceConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

    public static void main(String[] args) throws Exception {
        new JobApplicationServiceApp().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<JobApplicationServiceConfiguration> bootstrap) {
        bootstrap.addBundle(guice());
        bootstrap.addBundle(tenacity());
        swaggerDropwizard.onInitialize(bootstrap);
    }

    private TenacityConfiguredBundle<Configuration> tenacity() {
        return TenacityBundleBuilder.newBuilder()
                .configurationFactory(new JobApplicationServiceTenacityBundleConfigurationFactory())
                .build();
    }

    private GuiceBundle<JobApplicationServiceConfiguration> guice() {
        return GuiceBundle.<JobApplicationServiceConfiguration>newBuilder()
                .addModule(new JobApplicationServiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(JobApplicationServiceConfiguration.class)
                .build();
    }

    @Override
    public void run(JobApplicationServiceConfiguration configuration, Environment environment) throws Exception {
        swaggerDropwizard.onRun(configuration, environment, "localhost");
    }
}
