package org.olid16.infrastructure.events;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.olid16.domain.collections.Jobs;

import java.io.IOException;

public class UserEventConsumer implements Bundle{
    private final Jobs jobs;

    @Inject
    public UserEventConsumer(Jobs jobs) {
        this.jobs = jobs;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
    }

    @Override
    public void run(final Environment environment) {
        try {
            QueueingConsumer consumer = consumer();
            new Thread((() -> {
                while (true) {
                    try {
                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                        JsonObject message = JsonObject.readFrom(new String(delivery.getBody()));
                        String employerId = message.get("id").asString();
                        String name = message.get("name").asString();
                        jobs.updateEmployerName(employerId, name);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            })).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QueueingConsumer consumer() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("userEvents", "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, "userEvents", "");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        return consumer;
    }
}
