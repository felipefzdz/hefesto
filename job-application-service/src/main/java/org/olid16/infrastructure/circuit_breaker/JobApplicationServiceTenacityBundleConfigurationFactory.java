package org.olid16.infrastructure.circuit_breaker;

import com.google.common.collect.ImmutableMap;
import com.yammer.tenacity.core.bundle.TenacityBundleConfigurationFactory;
import com.yammer.tenacity.core.config.BreakerboxConfiguration;
import com.yammer.tenacity.core.config.TenacityConfiguration;
import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;
import io.dropwizard.Configuration;
import org.olid16.infrastructure.JobApplicationServiceConfiguration;

import java.util.Map;

import static org.olid16.infrastructure.circuit_breaker.JobApplicationServiceTenacityPropertyKey.*;

public class JobApplicationServiceTenacityBundleConfigurationFactory implements TenacityBundleConfigurationFactory<Configuration> {


    @Override
    public Map<TenacityPropertyKey, TenacityConfiguration> getTenacityConfigurations(Configuration configuration) {
        TenacityConfiguration config = ((JobApplicationServiceConfiguration) configuration).getJobApplicationServiceTenacityConfig();
        return  ImmutableMap.<TenacityPropertyKey, TenacityConfiguration>of(
                USER_SERVICE, config,
                JOB_SERVICE, config);
    }

    @Override
    public TenacityPropertyKeyFactory getTenacityPropertyKeyFactory(Configuration applicationConfiguration) {
        return new TenacityFactory();
    }

    @Override
    public BreakerboxConfiguration getBreakerboxConfiguration(Configuration configuration) {
        return ((JobApplicationServiceConfiguration)configuration).getBreakerboxConfiguration();
    }
}
