package org.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.olid16.domain.Users;
import org.olid16.infrastructure.repositories.MongoUsers;

import java.net.UnknownHostException;

public class UserServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Users.class).to(MongoUsers.class);
    }
    
    @Provides @Singleton
    DB provideDB(){
        try {
            return new MongoClient().getDB("userService");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
}
