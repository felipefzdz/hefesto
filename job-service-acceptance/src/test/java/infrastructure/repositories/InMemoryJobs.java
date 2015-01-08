package infrastructure.repositories;

import com.eclipsesource.json.JsonArray;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;

import java.util.HashMap;
import java.util.Iterator;
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
    public String by(String employerId) {
        JsonArray jsonArray = new JsonArray();
        Iterator<Job> it = jobs.get(employerId).iterator();
        while(it.hasNext()){
            jsonArray.add(it.next().toString());
        }
        return jsonArray.toString();
    }
}
