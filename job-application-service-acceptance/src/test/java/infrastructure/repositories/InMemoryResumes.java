package infrastructure.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.Content;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

import java.util.UUID;

public class InMemoryResumes implements Resumes{
    private Multimap<String, Resume> resumes = ArrayListMultimap.create();

    @Override
    public Resume add(Resume resume) {
        Resume resumeWithId = Resume.createResume(
                ResumeId.create(UUID.randomUUID().toString()),
                UserId.create(resume.userId()),
                Content.create(resume.content()));
        resumes.put(resumeWithId.id(), resumeWithId);
        return resumeWithId;
    }
}
