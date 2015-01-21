package org.olid16.domain.collections;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

import java.util.List;
import java.util.Optional;

public interface Jobs {
    JobId nextId();

    void add(Job job);

    List<Job> byEmployerId(String employerId);

    List<Job> all();

    void addJobseeker(JobId jobId, UserId jobseekerId);

    List<Job> byJobseekerId(String jobseekerId);

    void updateEmployerName(String employerId, String name);

    Optional<Job> byId(String id);
}
