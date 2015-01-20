package org.olid16.domain.values;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Job {
    
    public static Job create(JobId jobId, Title title){
        return new AutoValue_Job(jobId, title);
    }
    
    public abstract JobId jobId();
    public abstract Title title();
        
        
}
