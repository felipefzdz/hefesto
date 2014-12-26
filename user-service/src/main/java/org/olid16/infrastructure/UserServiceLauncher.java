package org.olid16.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.olid16.actions.CreateEmployer;
import org.olid16.infrastructure.dependency_injection.UserServiceModule;

public class UserServiceLauncher {
    
    public static void main(String[] args){
        Injector injector = Guice.createInjector(new UserServiceModule());
        CreateEmployer createEmployer = injector.getInstance(CreateEmployer.class);
        createEmployer.with("Test");
    }
}
