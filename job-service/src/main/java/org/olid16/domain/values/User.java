package org.olid16.domain.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

    @JsonCreator
    public static User create(@JsonProperty("person")Person person,
                              @JsonProperty("role")UserRole userRole, 
                              @JsonProperty("_id")String userId){
        return new AutoValue_User(person, userRole, userId);
    }

    @JsonProperty
    public abstract Person person();
    @JsonProperty
    public abstract UserRole userRole();
    @JsonProperty
    public abstract String userId();

    public boolean isEmployer() {
        return UserRole.EMPLOYER.equals(userRole());
    }

    public boolean isJobseeker() {
        return UserRole.JOBSEEKER.equals(userRole());
    }

    public String name() {
        return person().name();
    }
}
