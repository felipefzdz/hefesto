package org.olid16.domain.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

    @JsonCreator
    public static User create(@JsonProperty("person")Person person, @JsonProperty("role")UserRole userRole){
        return new AutoValue_User(person, userRole);
    }

    @JsonProperty
    public abstract Person person();
    @JsonProperty
    public abstract UserRole userRole();

    public boolean isEmployer() {
        return UserRole.EMPLOYER.equals(userRole());
    }
}
