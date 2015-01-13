package org.olid16.domain.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

    public static User create(Person person,
                              UserRole userRole,
                              UserId userId){
        return new AutoValue_User(person, userRole, userId);
    }

    public abstract Person person();
    public abstract UserRole userRole();
    public abstract UserId userId();

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
