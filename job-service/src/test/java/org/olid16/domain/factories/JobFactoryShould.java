package org.olid16.domain.factories;

import builders.JobBuilder;
import builders.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.Job;
import org.olid16.infrastructure.exceptions.ValidationException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;
import static utils.Assert.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class JobFactoryShould {


    @Test
    public void
    create_a_job_when_data_is_valid(){
        Job job = new JobFactory().create(UserBuilder.aUser().build(), "1234", JobBuilder.JobIdBuilder.aJobId().build());
        assertThat(job).isNotNull();
    }

    @Test public void
    throw_validation_exception_when_some_mandatory_field_is_not_present(){
        assertThrows(ValidationException.class, () -> new JobFactory().create(null, null, null));
    }
}
