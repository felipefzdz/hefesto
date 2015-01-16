package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Job {

    @JsonProperty
    private final String userId;
    @JsonProperty
    private final String title;
    @JsonProperty
    private final String id;
    @JsonProperty
    private final String userName;
    @JsonProperty
    private final List<String> jobseekers;


    @JsonCreator
    public Job(@JsonProperty("userId") String userId,
               @JsonProperty("title") String title,
               @JsonProperty("id") String id,
               @JsonProperty("userName") String userName,
               @JsonProperty("jobseekers") List<String> jobseekers) {
        this.userId = userId;
        this.title = title;
        this.id = id;
        this.userName = userName;
        this.jobseekers = jobseekers;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getJobseekers() {
        return jobseekers;
    }
}
