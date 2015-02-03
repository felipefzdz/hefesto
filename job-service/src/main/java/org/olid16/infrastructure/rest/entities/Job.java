package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Job {

    @JsonProperty
    private String userId;
    @JsonProperty
    private String title;
    @JsonProperty
    private String type;
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String userName;
    @JsonIgnore
    private List<String> jobseekers;

    public Job(String userId,
               String title,
               String id,
               String userName,
               String type,
               List<String> jobseekers) {
        this.userId = userId;
        this.title = title;
        this.id = id;
        this.userName = userName;
        this.type = type;
        this.jobseekers = jobseekers;
    }

    @JsonCreator
    public Job(@JsonProperty("userId") String userId,
               @JsonProperty("title") String title,
               @JsonProperty("type") String type) {
        this.userId = userId;
        this.title = title;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getUserName() {
        return userName;
    }

    @JsonProperty
    public List<String> getJobseekers() {
        return jobseekers;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public void setJobseekers(List<String> jobseekers) {
        this.jobseekers = jobseekers;
    }
}
