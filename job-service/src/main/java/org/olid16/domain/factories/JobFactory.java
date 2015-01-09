package org.olid16.domain.factories;

import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.Title;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.JsonEntity;

public class JobFactory {
    public Job create(JsonEntity jsonEntity, JobId jobId) {
        jsonEntity.validatePresenceOf("userId", "title");
        return new Job(jobId, userId(jsonEntity), title(jsonEntity));
    }
    
    public Job create(String jobId, String employerId, String title){
        return new Job(JobId.create(jobId), UserId.create(employerId), Title.create(title));
        
    }

    private UserId userId(JsonEntity jsonEntity) {
        return UserId.create(jsonEntity.get("userId"));
    }

    private Title title(JsonEntity jsonEntity) {
        return Title.create(jsonEntity.get("title"));
    }
}
