package org.olid16.infrastructure.events;

import com.rabbitmq.client.QueueingConsumer;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.exceptions.InfrastructureException;
import utils.Assert;

import static com.google.common.truth.Truth.assertThat;
import static com.rabbitmq.client.QueueingConsumer.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserEventConsumerShould {

    @Mock Jobs jobs;
    @Mock QueueingConsumer consumer;
    @Mock Delivery delivery;

    @Test public void
    update_employer_name_when_receiving_a_message() throws InterruptedException {
        given(delivery.getBody()).willReturn("{ \"id\" : \"1234\", \"name\" : \"test\" }".getBytes());
        given(consumer.nextDelivery()).willReturn(delivery);
        new UserEventConsumer(consumer, jobs).consumeMessage();
        verify(jobs).updateEmployerName("1234", "test");
    }
    
    @Test public void
    throw_infrastructure_exception_when_consumer_throws_exception() throws InterruptedException {
        doThrow(InterruptedException.class).when(consumer).nextDelivery();
        Assert.assertThrows(InfrastructureException.class, () -> new UserEventConsumer(consumer, jobs).consumeMessage());
    }
}