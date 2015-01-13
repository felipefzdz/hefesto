package builders;

import org.olid16.domain.values.Person;
import org.olid16.domain.entities.User;
import org.olid16.domain.values.UserId;
import org.olid16.domain.values.UserRole;

import static builders.UserBuilder.PersonBuilder.aPerson;
import static builders.UserBuilder.UserIdBuilder.*;


public class UserBuilder {

    private Person person;
    private UserRole role;
    private UserId userId;

    public UserBuilder(Person person, UserRole role, UserId userId) {
        this.person = person;
        this.role = role;
        this.userId = userId;
    }

    public static UserBuilder aUser(){
        return new UserBuilder(aPerson().build(), UserRole.UNKNOWN, aUserId().build());
    }
    
    public UserBuilder w(Person person){
        this.person = person;
        return this;
    }
    
    public UserBuilder w(UserId userId){
        this.userId = userId;
        return this;
        
    }

    public UserBuilder w(UserRole role) {
        this.role = role;
        return this;
    }

    public User build(){
        return User.createUser(person, role, userId);
    }


    public static class PersonBuilder {
        private String name;

        private PersonBuilder(String name) {
            this.name = name;
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder("Bob");
        }

        public Person build() {
            return Person.create(name);
        }
    }

    public static class UserIdBuilder {
        private String id;

        private UserIdBuilder(String id) {
            this.id = id;
        }

        public static UserIdBuilder aUserId() {
            return new UserIdBuilder("1234");
        }

        public UserId build() {
            return UserId.create(id);
        }
    }
}
