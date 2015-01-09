package org.olid16.domain.collections;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

import java.util.List;
import java.util.Optional;

public interface Jobs {
    JobId nextId();

    void add(Job job);

    Optional<String> byEmployerId(String employerId);

    Optional<String> all();

    void addJobseeker(JobId jobId, UserId jobseekerId);
}
