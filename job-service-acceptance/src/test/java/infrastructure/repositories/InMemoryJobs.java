package infrastructure.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryJobs implements Jobs {
    
    private Multimap<String, Job> jobs = ArrayListMultimap.create();

    @Override
    public JobId nextId() {
        return JobId.create("1234");
    }

    @Override
    public void add(Job job) {
        jobs.put(job.employerId(), job);
    }

    @Override
    public List<Job> by(String employerId) {
        return (List<Job>) jobs.get(employerId);
    }
}
