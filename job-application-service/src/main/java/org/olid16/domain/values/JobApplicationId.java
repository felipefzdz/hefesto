package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class JobApplicationId {
    
    public abstract String id();

    public static JobApplicationId create(String id) {
        return new AutoValue_JobApplicationId(id);
    }
}
