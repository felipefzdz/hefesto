package org.olid16.infrastructure;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.olid16.infrastructure.dependency_injection.UserServiceModule;

public class UserServiceApp extends Application<UserServiceConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new UserServiceApp().run(args);
    }
    
    @Override
    public void run(UserServiceConfiguration userServiceConfiguration, Environment environment) throws Exception {

    }

    @Override
    public void initialize(Bootstrap<UserServiceConfiguration> bootstrap) {
        bootstrap.addBundle(guice());
    }

    private GuiceBundle<UserServiceConfiguration> guice() {
        return GuiceBundle.<UserServiceConfiguration>newBuilder()
                .addModule(new UserServiceModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(UserServiceConfiguration.class)
                .build();
    }
}
