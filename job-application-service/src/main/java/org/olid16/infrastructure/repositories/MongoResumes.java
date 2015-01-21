package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.Resume;
import org.olid16.infrastructure.dependency_injection.ResumesDb;

import java.util.Optional;

public class MongoResumes implements Resumes{
    private final DBCollection resumes;
    private final ResumeAdapter resumeAdapter;

    @Inject
    public MongoResumes(@ResumesDb DBCollection resumes,
                        ResumeAdapter resumeAdapter) {
        this.resumes = resumes;
        this.resumeAdapter = resumeAdapter;
    }

    @Override
    public Resume add(Resume resume) {
        DBObject dbResume = resumeAdapter.toDbObject(resume);
        resumes.insert(dbResume);
        return resumeAdapter.fromDbObject(dbResume);
    }

    @Override
    public Optional<Resume> findById(String id) {
        DBObject dbObject = resumes.find(new BasicDBObject("_id", new ObjectId(id))).one();
        return dbObject == null ? Optional.empty() : Optional.of(resumeAdapter.fromDbObject(dbObject));
    }
}
