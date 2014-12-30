package org.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.repositories.MongoJobs;
import us.monoid.web.Resty;

import java.net.UnknownHostException;

public class JobServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Jobs.class).to(MongoJobs.class).in(Singleton.class);
    }
    
    @Provides @Singleton Resty resty(){
        return new Resty();
    }

    @Provides @Singleton DB provideDB(){
        try {
            return new MongoClient().getDB("userService");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
