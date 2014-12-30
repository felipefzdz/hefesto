package org.olid16.infrastructure;

import com.google.inject.Guice;
import org.olid16.infrastructure.dependency_injection.JobServiceModule;
import org.olid16.infrastructure.rest.Routes;

public class JobServiceLauncher {
    
    public static void main(String[] args){
        Guice.createInjector(new JobServiceModule()).getInstance(Routes.class).initialise();
        
    }
}
