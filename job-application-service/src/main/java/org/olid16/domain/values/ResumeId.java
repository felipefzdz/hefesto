package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ResumeId {

    public static ResumeId create(String id){
        return new AutoValue_ResumeId(id);

    }

    public abstract String id();
}