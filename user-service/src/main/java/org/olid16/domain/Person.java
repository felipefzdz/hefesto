package org.olid16.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Person {
    public static Person create(String name){
        return new AutoValue_Person(name);
    }

    abstract String name();
}
