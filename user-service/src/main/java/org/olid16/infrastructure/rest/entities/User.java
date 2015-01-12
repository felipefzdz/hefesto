package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final String role;


    @JsonCreator
    public User(@JsonProperty("name") String name,
                @JsonProperty("role") String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
