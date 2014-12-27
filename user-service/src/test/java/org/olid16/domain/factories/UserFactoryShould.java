package org.olid16.domain.factories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.entities.User;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.infrastructure.rest.JsonEntity;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static utils.Assert.assertThrows;

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
    throw_validation_exception_when_role_is_invalid(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willReturn("invalid");
        assertThrows(ValidationException.class, () -> new UserFactory().create(jsonEntity, null));
    }

    @Test public void
    throw_validation_exception_when_name_is_missing(){
        given(jsonEntity.get("name")).willReturn("");
        given(jsonEntity.get("role")).willReturn("employer");
        assertThrows(ValidationException.class, () -> new UserFactory().create(jsonEntity, null));
    }
    @Test public void
    throw_validation_exception_when_role_is_missing(){
        given(jsonEntity.get("name")).willReturn("Bob");
        given(jsonEntity.get("role")).willThrow(NullPointerException.class);
        assertThrows(ValidationException.class, () -> new UserFactory().create(jsonEntity, null));
    }
}
