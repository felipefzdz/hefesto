package builders;

import org.olid16.domain.Person;

public class PersonBuilder {
    private String name;

    private PersonBuilder(String name) {
        this.name = name;
    }

    public static PersonBuilder aPerson() {
        return new PersonBuilder("Bob");
    }

    public Person build() {
        return new Person(name);
    }
}
