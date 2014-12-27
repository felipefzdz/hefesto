package org.olid16.domain.factories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;
import org.olid16.infrastructure.exceptions.ValidationException;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;
import static utils.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryShould {

    @Mock JsonEntity jsonEntity;

    @Test public void
    create_a_user_when_data_is_valid(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willReturn("employer");
        User user = new UserFactory().create(jsonEntity, null);
        assertThat(user).isNotNull();
    }

    @Test public void
    throw_validation_exception_when_some_mandatory_field_is_not_present(){
        doThrow(new ValidationException("")).when(jsonEntity).validatePresenceOf(anyVararg());
        assertThrows(ValidationException.class, () -> new UserFactory().create(jsonEntity, null));
    }


}
