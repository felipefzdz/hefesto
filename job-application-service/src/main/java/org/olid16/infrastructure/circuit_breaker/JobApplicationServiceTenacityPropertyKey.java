package org.olid16.infrastructure.circuit_breaker;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;

public enum JobApplicationServiceTenacityPropertyKey implements TenacityPropertyKey {
    USER_SERVICE, JOB_SERVICE
}
