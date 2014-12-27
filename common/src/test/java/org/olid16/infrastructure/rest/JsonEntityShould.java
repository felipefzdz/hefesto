package org.olid16.infrastructure.rest;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import utils.Assert;
import org.olid16.infrastructure.exceptions.*;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class JsonEntityShould {

    @Mock JsonObject jsonObject;
    @Mock JsonValue jsonValue;

    @Test public void
    throw_validation_exception_when_field_is_not_present(){
        given(jsonObject.get("")).willThrow(NullPointerException.class);
        Assert.assertThrows(ValidationException.class, () -> new JsonEntity(jsonObject).validatePresenceOf(""));
    }

    @Test public void
    throw_validation_exception_when_field_is_empty(){
        given(jsonObject.get("")).willReturn(jsonValue);
        given(jsonValue.asString()).willReturn("");
        Assert.assertThrows(ValidationException.class, () -> new JsonEntity(jsonObject).validatePresenceOf(""));
    }


}
