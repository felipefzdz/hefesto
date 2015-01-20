package org.olid16.infrastructure.dependency_injection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.olid16.domain.collections.JobApplications;
import org.olid16.domain.collections.Resumes;
import org.olid16.infrastructure.circuit_breaker.commands.GetJobByIdCommandFactory;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.apis.UserApi;
import org.olid16.infrastructure.repositories.MongoJobApplications;
import org.olid16.infrastructure.repositories.MongoResumes;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import java.net.UnknownHostException;

public class JobApplicationServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
        install(new FactoryModuleBuilder()
                .build(GetJobByIdCommandFactory.class));
        bind(Resumes.class).to(MongoResumes.class).in(Singleton.class);
        bind(JobApplications.class).to(MongoJobApplications.class).in(Singleton.class);
    }

    @Provides @Singleton
    DB db(){
        try {
            return new MongoClient().getDB("jobApplicationService");
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
        
    }

    @Provides @Singleton @ResumesDb
    DBCollection resumes(DB db){
        return db.getCollection("resumes");
    }

    @Provides @Singleton @JobApplicationsDb
    DBCollection jobApplications(DB db){
        return db.getCollection("jobApplications");
    }
    
    @Provides @Singleton UserApi userAPi(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(jacksonConverter())
                .setEndpoint("http://localhost:8081")
                .build();
        return restAdapter.create(UserApi.class);
    }

    @Provides @Singleton JobApi jobApi(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(jacksonConverter())
                .setEndpoint("http://localhost:8082")
                .build();
        return restAdapter.create(JobApi.class);

    }

    private JacksonConverter jacksonConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new JacksonConverter(objectMapper);
    }


}
