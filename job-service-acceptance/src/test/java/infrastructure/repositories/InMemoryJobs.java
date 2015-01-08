package infrastructure.repositories;

import com.eclipsesource.json.JsonArray;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.*;

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
    public Optional<String> byEmployerId(String employerId) {
        Iterator<Job> it = jobs.get(employerId).iterator();
        return adapt(it);
    }

    @Override
    public Optional<String> all() {
        Iterator<Job> it = jobs.values().iterator();
        return adapt(it);
    }

    private Optional<String> adapt(Iterator<Job> it) {
        JsonArray jsonArray = new JsonArray();
        while(it.hasNext()){
            jsonArray.add(it.next().toString());
        }
        return Optional.of(jsonArray.toString());
    }
}
