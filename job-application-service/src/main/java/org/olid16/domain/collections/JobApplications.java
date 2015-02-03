package org.olid16.domain.collections;

import org.olid16.domain.entities.JobApplication;

import java.util.List;

public interface JobApplications {
    JobApplication add(JobApplication jobApplication);

    List<JobApplication> byJobseekerId(String jobseekerId);
}
