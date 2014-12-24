package builders;

import org.olid16.domain.Person;
import org.olid16.domain.User;
import org.olid16.domain.UserRole;

import static builders.PersonBuilder.*;

public class UserBuilder {

    private Person person;
    private UserRole role;

    public UserBuilder(Person person, UserRole role) {
        this.person = person;
        this.role = role;
    }

    public static UserBuilder aUser(){
        return new UserBuilder(aPerson().build(), UserRole.UNKNOWN);
    }
    
    public UserBuilder w(Person person){
        this.person = person;
        return this;
    }

    public UserBuilder w(UserRole role) {
        this.role = role;
        return this;
    }

    public User build(){
        return new User(person, role);
    }
}
