package org.olid16.infrastructure.clients.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {
    @JsonProperty
    private final String id;

    @JsonProperty
    private final String title;

    @JsonCreator
    public Job(@JsonProperty("id") String id, 
               @JsonProperty("title") String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
