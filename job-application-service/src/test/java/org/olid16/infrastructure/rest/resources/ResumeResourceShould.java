package org.olid16.infrastructure.rest.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.actions.CreateResume;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.adapters.ResumeAdapter;
import org.olid16.infrastructure.rest.entities.Resume;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.olid16.utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ResumeResourceShould {

    @Mock CreateResume createResume;
    @Mock ResumeAdapter resumeAdapter;

    @Test public void
    return_resume_id_when_create_resume() {
        given(resumeAdapter.fromDomain(null)).willReturn(new Resume("1234", null, null));
        Resume resume = new Resume(null, null, null);
        Resume createdResume = (Resume) new ResumeResource(createResume, resumeAdapter).create(resume).getEntity();
        assertThat(createdResume.getResumeId()).isNotNull();
    }

    @Test public void
    return_bad_request_when_there_is_a_domain_exception() {
        given(createResume.with(null)).willThrow(ValidationException.class);
        assertThrows(ValidationException.class, () -> new ResumeResource(createResume, resumeAdapter).create(new Resume(null, null, null)));
    }
}