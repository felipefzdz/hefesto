package org.olid16.infrastructure.clients.adapters;

import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.infrastructure.clients.entities.Job;

public class JobAdapter {
    public org.olid16.domain.values.Job fromClient(Job job) {
        return org.olid16.domain.values.Job.create(
                JobId.create(job.getId()),
                Title.create(job.getTitle())
        );
    }
}
