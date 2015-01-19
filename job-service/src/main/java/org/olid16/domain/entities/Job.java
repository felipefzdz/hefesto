package org.olid16.domain.entities;

import org.olid16.domain.values.*;

public class Job {

    private final JobId jobId;
    private final User user;
    private final Title title;
    private final Jobseekers jobseekers;
    private final JobType jobType;

    private Job(JobId jobId, User user, Title title, JobType jobType) {
        this.jobId = jobId;
        this.user = user;
        this.title = title;
        this.jobType = jobType;
        this.jobseekers = Jobseekers.create();
    }

    public Job(JobId jobId, User user, Title title, Jobseekers jobseekers, JobType jobType) {
        this.jobId = jobId;
        this.user = user;
        this.title = title;
        this.jobseekers = jobseekers;
        this.jobType = jobType;
    }

    public static Job createJob(JobId jobId, User user, Title title, JobType jobType) {
        return new Job(jobId, user, title, jobType);
    }

    public static Job createJob(JobId jobId, User user, Title title, JobType jobType, Jobseekers jobseekers) {
        return new Job(jobId, user, title, jobseekers, jobType);
    }

    public String id() {
        return jobId.id();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (!jobId.equals(job.jobId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return jobId.hashCode();
    }

    public String employerId() {
        return user.userId().id();
    }

    public String title() {
        return title.title();
    }

    public Jobseekers interestedJobseekers() {
        return jobseekers;
    }

    public void addJobseeker(UserId jobseekerId) {
        jobseekers.add(jobseekerId);
    }

    public String employerName() {
        return user.name();
    }

    public String type() {
        return jobType.toString();
    }
}
