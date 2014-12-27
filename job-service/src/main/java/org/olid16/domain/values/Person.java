package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Person {
    public static Person create(String name){
        return new AutoValue_Person(name);
    }

    public abstract String name();
}
