package org.olid16.infrastructure.events;

import com.google.inject.Inject;
import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class UserEventConsumerBundle implements Bundle{
    private final UserEventConsumer userEventConsumer;

    @Inject
    public UserEventConsumerBundle(UserEventConsumer userEventConsumer) {
        this.userEventConsumer = userEventConsumer;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(final Environment environment) {
        new Thread(userEventConsumer).start();
    }

}
