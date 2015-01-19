package org.olid16.domain.entities;

import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public class Resume {
    private final ResumeId resumeId;
    private final UserId userId;

    private Resume(ResumeId resumeId, UserId userId) {
        this.resumeId = resumeId;
        this.userId = userId;
    }

    public static Resume createResume(ResumeId resumeId, UserId userId) {
        return new Resume(resumeId, userId);
    }

    public String id() {
        return resumeId.id();
    }

    public String userId(){
        return userId.id();
        
    }
}
