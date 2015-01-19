package org.olid16.infrastructure.rest;

import org.olid16.infrastructure.rest.entities.Job;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class JobAdapter {
    
    public Job fromDomain(org.olid16.domain.entities.Job job){
        return new Job(job.employerId(), job.title(), job.id(), job.employerName(), jobseekers(job));
    }

    public List<Job> fromDomain(List<org.olid16.domain.entities.Job> jobs){
        return jobs.stream()
                .map(this::fromDomain)
                .collect(toList());
    }

    private List<String> jobseekers(org.olid16.domain.entities.Job job) {
        return job.interestedJobseekers().get().stream()
                .map(jobseeker -> jobseeker.id())
                .collect(toList());
    }
}
