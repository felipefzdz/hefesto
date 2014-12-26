package org.olid16.domain;

import com.eclipsesource.json.JsonObject;

public class UserJson {
    private final JsonObject jsonObject;

    public UserJson(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String get(String name) {
        return jsonObject.get(name).asString();
    }
}
