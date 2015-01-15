package org.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.olid16.domain.collections.Users;
import org.olid16.infrastructure.repositories.MongoUsers;

import java.io.IOException;
import java.net.UnknownHostException;

public class UserServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Users.class).to(MongoUsers.class).in(Singleton.class);
    }
    
    @Provides @Singleton
    DBCollection userCollection(){
        try {
            return new MongoClient().getDB("userService").getCollection("users");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }

    @Provides @Singleton
    Channel rabbitChannel() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("userEvents", "fanout");
        return channel;
    }

}
