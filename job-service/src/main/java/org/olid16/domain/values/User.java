package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {

    public static User create(Person person, UserRole userRole){
        return new AutoValue_User(person, userRole);
    }

    public abstract Person person();
    public abstract UserRole userRole();
}
