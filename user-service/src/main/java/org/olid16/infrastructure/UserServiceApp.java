package org.olid16.infrastructure;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerDropwizard;
import org.olid16.infrastructure.dependency_injection.UserServiceModule;

public class UserServiceApp extends Application<UserServiceConfiguration> {

    private final SwaggerDropwizard swaggerDropwizard = new SwaggerDropwizard();
    
    public static void main(String[] args) throws Exception {
        new UserServiceApp().run(args);
    }
    
    @Override
    public void run(UserServiceConfiguration configuration, Environment environment) throws Exception {
        swaggerDropwizard.onRun(configuration, environment, "localhost");
    }

    @Override
    public void initialize(Bootstrap<UserServiceConfiguration> bootstrap) {
        bootstrap.addBundle(guice());
        swaggerDropwizard.onInitialize(bootstrap);
    }

    private GuiceBundle<UserServiceConfiguration> guice() {
        return GuiceBundle.<UserServiceConfiguration>newBuilder()
                .addModule(new UserServiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(UserServiceConfiguration.class)
                .build();
    }
}
