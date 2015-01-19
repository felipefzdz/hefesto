package org.olid16.domain.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Person {
    
    @JsonCreator
    public static Person create(@JsonProperty("name")String name){
        return new AutoValue_Person(name);
    }

    @JsonProperty
    public abstract String name();
}
