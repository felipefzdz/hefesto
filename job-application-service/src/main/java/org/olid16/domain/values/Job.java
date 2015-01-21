package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Job {
    
    public static Job create(JobId jobId, Title title, JobType jobType){
        return new AutoValue_Job(jobId, title, jobType);
    }
    
    public abstract JobId jobId();
    public abstract Title title();
    public abstract JobType jobType();
        
        
}
