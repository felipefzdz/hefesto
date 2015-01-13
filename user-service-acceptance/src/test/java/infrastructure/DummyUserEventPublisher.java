package infrastructure;

import com.google.inject.Inject;
import com.rabbitmq.client.Channel;
import org.olid16.infrastructure.events.UserEventPublisher;

public class DummyUserEventPublisher extends UserEventPublisher{
    @Inject
    public DummyUserEventPublisher(Channel channel) {
        super(channel);
    }
}
