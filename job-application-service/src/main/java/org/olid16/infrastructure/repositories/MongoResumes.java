package org.olid16.infrastructure.repositories;

import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.Resume;

public class MongoResumes implements Resumes{
    private final DBCollection resumes;
    private final ResumeAdapter resumeAdapter;

    @Inject
    public MongoResumes(DBCollection resumes, ResumeAdapter resumeAdapter) {
        this.resumes = resumes;
        this.resumeAdapter = resumeAdapter;
    }

    @Override
    public Resume add(Resume resume) {
        DBObject dbResume = resumeAdapter.toDbObject(resume);
        resumes.insert(dbResume);
        return resumeAdapter.fromDbObject(dbResume);
    }
}
