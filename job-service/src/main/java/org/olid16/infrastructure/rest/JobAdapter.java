package org.olid16.infrastructure.rest;

import org.olid16.domain.values.*;
import org.olid16.infrastructure.rest.entities.Job;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class JobAdapter {
    
    public Job fromDomain(org.olid16.domain.entities.Job job){
        return new Job(job.employerId(), job.title(), job.id(), job.employerName(), job.type(), jobseekers(job));
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

    public org.olid16.domain.entities.Job toDomain(Job job) {
        return org.olid16.domain.entities.Job.createJob(jobIdFrom(job), userFrom(job), titleFrom(job), jobTypeFrom(job));
    }

    private JobType jobTypeFrom(Job job) {
        try {
            return JobType.valueOf(job.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            return JobType.UNKNOWN;
        }
    }

    private Title titleFrom(Job job) {
        return Title.create(job.getTitle());
    }

    private User userFrom(Job job) {
        return User.create(person(job), UserRole.UNKNOWN, UserId.create(job.getUserId()));
    }

    private Person person(Job job) {
        String userName = job.getUserName() == null ? "" : job.getUserName();
        return Person.create(userName);
    }

    private JobId jobIdFrom(Job job) {
        String id = job.getId() == null ? "" : job.getId();
        return JobId.create(id);
    }
}
