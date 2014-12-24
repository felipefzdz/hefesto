package builders;

import org.olid16.domain.Person;
import org.olid16.domain.User;

import static builders.PersonBuilder.*;

public class UserBuilder {

    private Person person;

    public UserBuilder(Person person) {
        this.person = person;
    }

    public static UserBuilder aUser(){
        return new UserBuilder(aPerson().build());
    }
    
    public UserBuilder w(Person person){
        this.person = person;
        return this;
    }
    
    public User build(){
        return new User(person);
    }
}
