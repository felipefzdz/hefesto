package org.olid16.infrastructure.rest.adapters;

import org.junit.Test;
import org.olid16.domain.entities.Resume;

import static com.google.common.truth.Truth.assertThat;
import static org.olid16.builders.ResumeBuilder.aResume;

public class ResumeAdapterShould {
    
    @Test public void
    adapt_to_domain(){
        Resume resume = new ResumeAdapter().toDomain(new org.olid16.infrastructure.rest.entities.Resume("resumeId", "jobseekerId", "content"));
        assertThat(resume.id()).is("resumeId");
    }
    
    @Test public void
    adapt_from_domain() {
        org.olid16.infrastructure.rest.entities.Resume resume = new ResumeAdapter().fromDomain(aResume().build());
        assertThat(resume.getResumeId()).is(aResume().build().id());
    }
    
}