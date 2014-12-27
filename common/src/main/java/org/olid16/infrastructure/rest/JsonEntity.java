package org.olid16.infrastructure.rest;

import com.eclipsesource.json.JsonObject;

public class JsonEntity {
    private final JsonObject jsonObject;

    public JsonEntity(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String get(String name) {
        return jsonObject.get(name).asString();
    }
}
