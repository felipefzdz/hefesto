package org.olid16.domain.entities;

import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.UserId;

public class Job {

    private final JobId jobId;
    private final UserId userId;
    private final Title title;
    private final Jobseekers jobseekers;

    public Job(JobId jobId, UserId userId, Title title) {
        this.jobId = jobId;
        this.userId = userId;
        this.title = title;
        this.jobseekers = Jobseekers.create();
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
        return userId.id();
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
}
