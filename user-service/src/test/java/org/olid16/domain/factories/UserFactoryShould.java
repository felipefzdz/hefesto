package org.olid16.domain.factories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.exceptions.ValidationException;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;
import static utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryShould {

    @Test public void
    create_a_user_when_role_is_employer(){
        User user = new UserFactory().create("Bob", "employer", null);
        assertThat(user).isNotNull();
    }

    @Test public void
    create_a_user_when_role_is_jobseeker(){
        User user = new UserFactory().create("Bob", "jobseeker", null);
        assertThat(user).isNotNull();
    }

    @Test public void
    throw_validation_exception_when_some_mandatory_field_is_not_present(){
        assertThrows(ValidationException.class, () -> new UserFactory().create("", "", null));
    }

    @Test public void
    throw_validation_exception_when_role_is_not_valid(){
        assertThrows(ValidationException.class, () -> new UserFactory().create("Bob", "whatever", null));
    }

}
