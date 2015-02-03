package org.olid16.infrastructure.rest.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobApplication {
    
    @JsonProperty
    private String jobId;
    @JsonProperty
    private String jobseekerId;
    @JsonProperty
    private String resumeId;
    @JsonIgnore
    private String id;

    public JobApplication(String jobId,
                          String jobseekerId,
                          String id,
                          String resumeId) {
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.id = id;
        this.resumeId = resumeId;
    }

    @JsonCreator
    public JobApplication(@JsonProperty("jobId") String jobId,
                          @JsonProperty("jobseekerId") String jobseekerId,
                          @JsonProperty("resumeId") String resumeId) {
        this.jobId = jobId;
        this.jobseekerId = jobseekerId;
        this.resumeId = resumeId;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobseekerId() {
        return jobseekerId;
    }

    public String getResumeId() {
        return resumeId;
    }

    @JsonProperty
    public String getId() {
        return id;
    }

    @JsonIgnore
    public void setId(String id) {
        this.id = id;
    }
}
