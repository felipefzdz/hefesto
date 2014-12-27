package org.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import us.monoid.web.Resty;

public class JobServiceModule extends AbstractModule{
    @Override
    protected void configure() {
        
    }
    
    @Provides Resty resty(){
        return new Resty();
    }
}
