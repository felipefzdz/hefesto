package org.olid16.domain.entities;

import org.olid16.domain.values.Content;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public class Resume {
    private final ResumeId resumeId;
    private final UserId userId;
    private final Content content;

    private Resume(ResumeId resumeId, UserId userId, Content content) {
        this.resumeId = resumeId;
        this.userId = userId;
        this.content = content;
    }

    public static Resume createResume(ResumeId resumeId, UserId userId, Content content) {
        return new Resume(resumeId, userId, content);
    }

    public String id() {
        return resumeId.id();
    }

    public String userId(){
        return userId.id();
    }
    
    public String content(){
        return content.value();
        
    }
}
