package org.olid16.builders;

import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.ResumeId;
import org.olid16.domain.values.UserId;

public class ResumeBuilder {
    
    private ResumeId resumeId;
    private UserId userId;

    public ResumeBuilder(ResumeId resumeId, UserId userId) {
        this.resumeId = resumeId;
        this.userId = userId;
    }
    
    public static ResumeBuilder aResume(){
        return new ResumeBuilder(ResumeIdBuilder.aResumeId().build(), UserIdBuilder.aUserId().build());
        
    }
    
    public ResumeBuilder w(ResumeId resumeId){
        this.resumeId = resumeId;
        return this;
    }
    
    public Resume build(){
        return Resume.createResume(resumeId, userId);
        
    }

    private static class ResumeIdBuilder {
        private final String id;

        private ResumeIdBuilder(String id) {
            this.id = id;
        }

        public static ResumeIdBuilder aResumeId() {
            return new ResumeIdBuilder("1234");
        }

        public ResumeId build() {
            return ResumeId.create(id);
        }
    }

    private static class UserIdBuilder {
        private final String id;

        private UserIdBuilder(String id) {
            this.id = id;
        }

        public static UserIdBuilder aUserId() {
            return new UserIdBuilder("1234");
        }

        public UserId build() {
            return UserId.create(id);
        }
    }
}
