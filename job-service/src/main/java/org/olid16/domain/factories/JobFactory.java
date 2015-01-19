package org.olid16.domain.factories;

import com.google.common.base.Strings;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.*;
import org.olid16.infrastructure.exceptions.ValidationException;

public class JobFactory {


    public Job create(User user, String title, JobId jobId, String jobType) {
        if(Strings.isNullOrEmpty(title)){
            throw new ValidationException("title is mandatory");
        }
        return Job.createJob(jobId, user, Title.create(title), JobType.valueOf(jobType.toUpperCase()));
    }
}
