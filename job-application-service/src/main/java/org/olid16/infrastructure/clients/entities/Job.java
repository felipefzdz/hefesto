package org.olid16.infrastructure.clients.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String title;
    @JsonProperty
    private String type;

    @JsonCreator
    public Job(@JsonProperty("id") String id, 
               @JsonProperty("title") String title,
               @JsonProperty("type") String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
