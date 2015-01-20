package infrastructure.clients;

import org.olid16.domain.values.JobId;
import org.olid16.infrastructure.clients.apis.JobApi;
import org.olid16.infrastructure.clients.entities.Job;
import retrofit.http.Path;

import java.util.HashMap;
import java.util.Map;

public class InMemoryJobApi  implements JobApi{
    private Map<JobId, Job> jobs = new HashMap<>();

    public void add(Job job) {
        jobs.put(JobId.create(job.getId()), job);
    }

    @Override
    public Job getBy(@Path("jobId") String jobId) {
        return jobs.get(JobId.create(jobId));
    }
}
