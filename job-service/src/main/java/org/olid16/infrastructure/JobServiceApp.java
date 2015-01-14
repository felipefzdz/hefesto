package org.olid16.infrastructure;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.yammer.tenacity.core.bundle.TenacityBundleBuilder;
import com.yammer.tenacity.core.bundle.TenacityConfiguredBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.olid16.infrastructure.circuit_breaker.JobServiceTenacityBundleConfigurationFactory;
import org.olid16.infrastructure.dependency_injection.JobServiceModule;

public class JobServiceApp extends Application<JobServiceConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();

    public static void main(String[] args) throws Exception {
        new JobServiceApp().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        bootstrap.addBundle(guice());
        bootstrap.addBundle(tenacity());
        swaggerDropwizard.onInitialize(bootstrap);
    }

    private TenacityConfiguredBundle<Configuration> tenacity() {
        return TenacityBundleBuilder.newBuilder()
                .configurationFactory(new JobServiceTenacityBundleConfigurationFactory())
                .build();
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
