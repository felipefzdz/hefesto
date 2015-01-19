package org.olid16.infrastructure.dependency_injection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import org.olid16.infrastructure.circuit_breaker.commands.GetUserByIdCommandFactory;
import org.olid16.infrastructure.clients.UserApi;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

public class JobApplicationServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(GetUserByIdCommandFactory.class));
    }
    
    @Provides @Singleton UserApi userClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(jacksonConverter())
                .setEndpoint("http://localhost:8081")
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
