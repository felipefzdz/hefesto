package org.olid16.infrastructure.events;

import com.google.inject.Inject;
import com.rabbitmq.client.Channel;
import org.olid16.domain.events.UserEvent;

import java.io.IOException;

public class UserEventPublisher {
    private final Channel channel;

    @Inject
    public UserEventPublisher(Channel channel) {
        this.channel = channel;
    }

    public void publish(UserEvent event) {
        try {
            channel.basicPublish("userEvents", "", null, event.message().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
