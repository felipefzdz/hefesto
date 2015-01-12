package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Job {

    @JsonProperty
    private final String userId;
    @JsonProperty
    private final String title;


    @JsonCreator
    public Job(@JsonProperty("userId") String userId,
                @JsonProperty("title") String title) {
        this.userId = userId;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
