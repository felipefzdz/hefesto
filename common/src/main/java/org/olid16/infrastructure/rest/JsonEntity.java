package org.olid16.infrastructure.rest;

import com.eclipsesource.json.JsonObject;
import org.olid16.infrastructure.exceptions.ValidationException;

import static com.google.common.base.Strings.*;

public class JsonEntity {
    private final JsonObject jsonObject;

    public JsonEntity(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String get(String name) {
        return jsonObject.get(name).asString();
    }

    public void validatePresenceOf(String... fields){
        for(String field: fields){
            validatePresenceOf(field);
        }
    }

    private void validatePresenceOf(String field) {
        try {
            if(isNullOrEmpty(this.get(field))){
                throw new ValidationException(String.format("%s is mandatory", field));
            }
        } catch (NullPointerException e) {
            throw new ValidationException(String.format("%s is mandatory", field));
        }
    }
}
