package org.olid16.domain.factories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.exceptions.ValidationException;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.rest.JsonEntity;
import utils.Assert;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserIdFactoryShould {

    @Mock JsonEntity jsonEntity;

    @Test public void
    create_a_user_id_when_data_is_valid(){
        given(jsonEntity.get("userId")).willReturn("1234");
        UserId userId = new UserIdFactory().create(jsonEntity);
        assertThat(userId).isNotNull();
        
    }

    @Test public void
    throw_a_validation_exception_when_some_mandatory_field_is_not_present(){
        doThrow(new ValidationException("")).when(jsonEntity).validatePresenceOf(anyVararg());
        Assert.assertThrows(ValidationException.class, () -> new UserIdFactory().create(jsonEntity));
        
    }
}
