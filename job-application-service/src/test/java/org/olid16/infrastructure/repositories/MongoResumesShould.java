package org.olid16.infrastructure.repositories;

import com.mongodb.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.builders.ResumeBuilder;
import org.olid16.domain.entities.Resume;

import java.util.Optional;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.olid16.builders.ResumeBuilder.*;
import static org.olid16.builders.ResumeBuilder.aResume;

@RunWith(MockitoJUnitRunner.class)
public class MongoResumesShould {

    @Mock DBCollection dbCollection;
    @Mock ResumeAdapter resumeAdapter;
    @Mock DBCursor cursor;

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

    @Test public void
    return_resume_by_id(){
        given(cursor.one()).willReturn(new BasicDBObject());
        given(dbCollection.find(any())).willReturn(cursor);
        given(resumeAdapter.fromDbObject(any())).willReturn(aResume().build());
        Optional<Resume> resume = new MongoResumes(dbCollection, resumeAdapter).findById("54bfa040bee8ae7c226265c0");
        assertThat(resume.isPresent()).isTrue();
        
    }

    @Test public void
    return_empty_resume_by_id_when_not_found(){
        given(cursor.one()).willReturn(null);
        given(dbCollection.find(any())).willReturn(cursor);
        Optional<Resume> resume = new MongoResumes(dbCollection, resumeAdapter).findById("54bfa040bee8ae7c226265c0");
        assertThat(resume.isPresent()).isFalse();

    }
}