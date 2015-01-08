package org.olid16.domain.collections;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.List;

public interface Jobs {
    JobId nextId();

    void add(Job job);

    String by(String employerId);
}
