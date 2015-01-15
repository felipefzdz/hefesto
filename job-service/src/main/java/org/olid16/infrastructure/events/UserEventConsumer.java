package org.olid16.infrastructure.events;

import com.eclipsesource.json.JsonObject;
import com.google.inject.Inject;
import com.rabbitmq.client.QueueingConsumer;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.exceptions.InfrastructureException;

public class UserEventConsumer implements Runnable {
    private final QueueingConsumer consumer;
    private final Jobs jobs;

    @Inject
    public UserEventConsumer(QueueingConsumer consumer, Jobs jobs) {
        this.consumer = consumer;
        this.jobs = jobs;
    }


    @Override
    public void run() {
        while (true) {
            consumeMessage();
        }
    }

    public void consumeMessage() {
        try {
            JsonObject message = nextMessage();
            jobs.updateEmployerName(idFrom(message), nameFrom(message));
        } catch (InterruptedException | RuntimeException e) {
            throw new InfrastructureException("Error while consuming message", e);
        }
    }

    private JsonObject nextMessage() throws InterruptedException {
        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
        return JsonObject.readFrom(new String(delivery.getBody()));
    }

    private String idFrom(JsonObject message) {
        return message.get("id").asString();
    }

    private String nameFrom(JsonObject message) {
        return message.get("name").asString();
    }
}
