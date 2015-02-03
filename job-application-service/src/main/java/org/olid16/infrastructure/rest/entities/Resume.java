package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {
    
    @JsonIgnore
    private String resumeId;
    @JsonProperty
    private String jobseekerId;
    @JsonProperty
    private String content;

    public Resume(String resumeId,
                  String jobseekerId,
                  String content) {
        this.resumeId = resumeId;
        this.jobseekerId = jobseekerId;
        this.content = content;
    }

    @JsonCreator
    public Resume(@JsonProperty("jobseekerId") String jobseekerId,
                  @JsonProperty("content") String content) {
        this.jobseekerId = jobseekerId;
        this.content = content;
    }

    public String getJobseekerId() {
        return jobseekerId;
    }

    public String getContent() {
        return content;
    }

    @JsonProperty
    public String getResumeId() {
        return resumeId;
    }

    @JsonIgnore
    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
}
