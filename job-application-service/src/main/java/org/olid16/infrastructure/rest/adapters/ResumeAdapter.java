package org.olid16.infrastructure.rest.adapters;

import org.olid16.domain.values.Content;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.entities.Resume;

public class ResumeAdapter {
    public org.olid16.domain.entities.Resume toDomain(Resume resume) {
        return org.olid16.domain.entities.Resume.createResume(
                resumeIdFrom(resume), 
                userIdFrom(resume), 
                contentFrom(resume));
    }

    private Content contentFrom(Resume resume) {
        return Content.create(resume.getContent());
    }

    private ResumeId resumeIdFrom(Resume resume) {
        String resumeId = resume.getResumeId() == null ? "" : resume.getResumeId();
        return ResumeId.create(resumeId);
    }

    private UserId userIdFrom(Resume resume) {
        return UserId.create(resume.getJobseekerId());
    }

    public Resume fromDomain(org.olid16.domain.entities.Resume resume) {
        return new Resume(resume.id(), resume.userId(), resume.content());
    }
}
