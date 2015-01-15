package org.olid16.infrastructure.events;

import com.rabbitmq.client.Channel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.infrastructure.exceptions.InfrastructureException;
import utils.Assert;

import java.io.IOException;

import static builders.UserBuilder.aUser;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.olid16.domain.events.UpdateUserEvent.createUpdateUserEvent;

@RunWith(MockitoJUnitRunner.class)
public class UserEventPublisherShould {

    @Mock Channel channel;

    @Test public void
    publish_message() throws IOException {
        new UserEventPublisher(channel).publish(createUpdateUserEvent(aUser().build()));
        verify(channel).basicPublish(anyString(), anyString(), any(), any());
    }
    
    @Test public void
    throw_infrastructure_exception_when_channel_fails() throws IOException {
        doThrow(IOException.class).when(channel).basicPublish(anyString(), anyString(), any(), any());
        Assert.assertThrows(InfrastructureException.class, () -> new UserEventPublisher(channel).publish(createUpdateUserEvent(aUser().build())));
    }
    
}