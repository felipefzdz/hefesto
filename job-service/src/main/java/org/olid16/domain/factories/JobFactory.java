package org.olid16.domain.factories;

import com.google.common.base.Strings;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.ValidationException;

public class JobFactory {


    public Job create(User user, String title, JobId jobId) {
        if(Strings.isNullOrEmpty(title)){
            throw new ValidationException("title is mandatory");
        }
        return Job.createJob(jobId, user, Title.create(title));
    }
}
