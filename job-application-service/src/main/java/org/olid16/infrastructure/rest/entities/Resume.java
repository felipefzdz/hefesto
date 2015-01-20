package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Resume {
    
    @JsonProperty
    private final String resumeId;
    @JsonProperty
    private final String jobseekerId;
    @JsonProperty
    private final String content;

    @JsonCreator
    public Resume(@JsonProperty("resumeId") String resumeId,
                  @JsonProperty("jobseekerId") String jobseekerId,
                  @JsonProperty("content") String content) {
        this.resumeId = resumeId;
        this.jobseekerId = jobseekerId;
        this.content = content;
    }

    public String getResumeId() {
        return resumeId;
    }

    public String getJobseekerId() {
        return jobseekerId;
    }

    public String getContent() {
        return content;
    }
}
