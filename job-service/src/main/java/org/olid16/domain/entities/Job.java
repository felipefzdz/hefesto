package org.olid16.domain.entities;

import org.olid16.domain.values.JobId;

public class Job {

    private final JobId jobId;

    public Job(JobId jobId) {
        this.jobId = jobId;
    }

    public String id() {
        return jobId.id();
    }
}
