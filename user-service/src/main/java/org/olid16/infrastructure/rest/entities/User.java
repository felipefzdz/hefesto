package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final String role;
    @JsonProperty
    private final String id;


    @JsonCreator
    public User(@JsonProperty("name") String name,
                @JsonProperty("role") String role,
                @JsonProperty("id") String id) {
        this.name = name;
        this.role = role;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
    
    public String getId() {return id;}
}
