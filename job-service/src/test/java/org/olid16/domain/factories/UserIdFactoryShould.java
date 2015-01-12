package org.olid16.domain.factories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.ValidationException;
import utils.Assert;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserIdFactoryShould {


    @Test public void
    create_a_user_id_when_data_is_valid(){
        UserId userId = new UserIdFactory().create("1234");
        assertThat(userId).isNotNull();
        
    }

    @Test public void
    throw_a_validation_exception_when_some_mandatory_field_is_not_present(){
        Assert.assertThrows(ValidationException.class, () -> new UserIdFactory().create(""));
        
    }
}
