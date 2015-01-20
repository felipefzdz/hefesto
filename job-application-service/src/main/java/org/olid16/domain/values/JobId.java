package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class JobId {

    public static JobId create(String id){
        return new AutoValue_JobId(id);

    }

    public abstract String id();
}