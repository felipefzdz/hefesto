package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty
    private String name;
    @JsonProperty
    private String role;
    @JsonIgnore
    private String id;

    public User(String name, String role, String id) {
        this.name = name;
        this.role = role;
        this.id = id;
    }

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

    @JsonProperty
    public String getId() {return id;}

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }
}
