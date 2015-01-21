package org.olid16.infrastructure.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.Content;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public class ResumeAdapter {
    public DBObject toDbObject(Resume resume) {
        BasicDBObject dbObject = new BasicDBObject("jobseekerId", resume.userId());
        dbObject.append("content", resume.content());
        return dbObject;
    }

    public Resume fromDbObject(DBObject dbResume) {
        return Resume.createResume(
                resumeIdFrom(dbResume),
                userIdFrom(dbResume),
                contentFrom(dbResume));
    }


    private Content contentFrom(DBObject dbResume) {
        return Content.create(extractField(dbResume, "content"));
    }

    private UserId userIdFrom(DBObject dbResume) {
        return UserId.create(extractField(dbResume, "jobseekerId"));
    }

    private ResumeId resumeIdFrom(DBObject dbResume) {
        return ResumeId.create(extractField(dbResume, "_id"));
    }

    private String extractField(DBObject dbObject, String field) {
        return dbObject.get(field).toString();
    }
}
