package org.olid16.domain.entities;

import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;

public class Job {

    private final JobId jobId;
    private final User user;
    private final Title title;
    private final Jobseekers jobseekers;

    private Job(JobId jobId, User user, Title title) {
        this.jobId = jobId;
        this.user = user;
        this.title = title;
        this.jobseekers = Jobseekers.create();
    }

    public Job(JobId jobId, User user, Title title, Jobseekers jobseekers) {
        this.jobId = jobId;
        this.user = user;
        this.title = title;
        this.jobseekers = jobseekers;
    }

    public static Job createJob(JobId jobId, User user, Title title) {
        return new Job(jobId, user, title);
    }

    public static Job createJob(JobId jobId, User user, Title title, Jobseekers jobseekers) {
        return new Job(jobId, user, title, jobseekers);
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
}
