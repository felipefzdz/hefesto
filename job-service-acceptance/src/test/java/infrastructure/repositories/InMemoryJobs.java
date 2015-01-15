package infrastructure.repositories;

import com.eclipsesource.json.JsonArray;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.olid16.domain.collections.Jobs;
import org.olid16.domain.entities.Job;
import org.olid16.domain.values.JobId;
import org.olid16.domain.values.UserId;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
    public List<Job> byEmployerId(String employerId) {
        return new ArrayList<>(jobs.get(employerId));
    }

    @Override
    public List<Job> all() {
        return new ArrayList<>(jobs.values());
    }

    @Override
    public void addJobseeker(JobId jobId, UserId jobseekerId) {
        Job job = byId(jobId.id());
        job.addJobseeker(jobseekerId);
    }

    @Override
    public List<Job> byJobseekerId(String jobseekerId) {
        return jobs.values().stream()
                .filter(job -> job.interestedJobseekers().contains(UserId.create(jobseekerId)))
                .collect(toList());
    }

    @Override
    public void updateEmployerName(String employerId, String name) {

    }

    public Job byId(String jobId) {
        return jobs.values().stream()
                .filter(job -> jobId.equals(job.id()))
                .findFirst().get();
    }
    
    public void clear(){
        jobs.clear();
        
    }
}
