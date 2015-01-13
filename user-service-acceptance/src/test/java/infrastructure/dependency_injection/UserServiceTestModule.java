package infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.rabbitmq.client.Channel;
import infrastructure.DummyChannel;
import infrastructure.DummyUserEventPublisher;
import infrastructure.InMemoryUsers;
import org.olid16.domain.collections.Users;
import org.olid16.infrastructure.events.UserEventPublisher;

public class UserServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Users.class).to(InMemoryUsers.class).in(Singleton.class);
        bind(UserEventPublisher.class).to(DummyUserEventPublisher.class);
        bind(Channel.class).to(DummyChannel.class);
    }
}
