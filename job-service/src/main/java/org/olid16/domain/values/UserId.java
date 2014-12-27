package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class UserId {

    public static UserId create(String id){
        return new AutoValue_UserId(id);

    }

    public abstract String id();
}