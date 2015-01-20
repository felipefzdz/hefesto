package org.olid16.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class JobApplicationServiceConfiguration extends Configuration {

    @NotNull
    private BreakerboxConfiguration breakerboxConfiguration;

    @NotNull
    private TenacityConfiguration jobApplicationServiceTenacityConfig;

    @JsonProperty
    public BreakerboxConfiguration getBreakerboxConfiguration() {
        return breakerboxConfiguration;
    }

    @JsonProperty
    public TenacityConfiguration getJobApplicationServiceTenacityConfig() {
        return jobApplicationServiceTenacityConfig;
    }
    
    
}
