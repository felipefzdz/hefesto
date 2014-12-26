package org.olid16.infrastructure;

import com.google.inject.Guice;
import org.olid16.infrastructure.dependency_injection.UserServiceModule;
import org.olid16.infrastructure.rest.Routes;

public class UserServiceLauncher {
    
    public static void main(String[] args){
        Guice.
            createInjector(new UserServiceModule()).
            getInstance(Routes.class).
            initialise();

    }
}
