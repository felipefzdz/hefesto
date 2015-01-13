package org.olid16.infrastructure.dependency_injection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.olid16.domain.collections.Jobs;
import org.olid16.infrastructure.clients.UserApi;
import org.olid16.infrastructure.repositories.MongoJobs;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.net.UnknownHostException;

public class JobServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Jobs.class).to(MongoJobs.class).in(Singleton.class);
    }
    
    @Provides @Singleton DB provideDB(){
        try {
            return new MongoClient().getDB("jobService");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Provides @Singleton
    UserApi userClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(jacksonConverter())
                .setEndpoint("http://localhost:8080")
                .build();
        return restAdapter.create(UserApi.class);
        
    }

    private JacksonConverter jacksonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new JacksonConverter(objectMapper);
    }


}
