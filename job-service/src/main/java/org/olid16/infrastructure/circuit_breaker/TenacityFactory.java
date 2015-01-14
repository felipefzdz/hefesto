package org.olid16.infrastructure.circuit_breaker;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class TenacityFactory implements TenacityPropertyKeyFactory {
    @Override
    public TenacityPropertyKey from(String s) {
        return JobServiceTenacityPropertyKey.valueOf(s.toUpperCase());
    }
}
