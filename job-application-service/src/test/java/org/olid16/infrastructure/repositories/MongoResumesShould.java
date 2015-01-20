package org.olid16.infrastructure.repositories;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.Resume;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.ResumeBuilder.aResume;

@RunWith(MockitoJUnitRunner.class)
public class MongoResumesShould {

    @Mock DBCollection dbCollection;
    @Mock ResumeAdapter resumeAdapter;

    @Test public void
    add_a_resume(){
        new MongoResumes(dbCollection, resumeAdapter).add(null);
       verify(dbCollection).insert((DBObject) null);
    }
    
    @Test public void
    return_resume_id_when_adding_a_resume() {
        given(resumeAdapter.fromDbObject(null)).willReturn(aResume().build());
        Resume resume = new MongoResumes(dbCollection, resumeAdapter).add(null);
        assertThat(resume.id()).isNotNull();
    }
}