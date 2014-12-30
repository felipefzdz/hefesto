package org.olid16.infrastructure.clients;

import org.olid16.domain.values.Person;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserRole;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;

import java.io.IOException;

public class UserAdapter {
    public User from(JSONResource resource) throws JSONException, IOException {
        JSONObject object = resource.object();
        return User.create(person(object), userRole(object));
    }

    private Person person(JSONObject jsonObject) throws JSONException {
        return Person.create(jsonObject.getJSONObject("person").getString("name"));
    }

    private UserRole userRole(JSONObject jsonObject) throws JSONException {
        return UserRole.valueOf(jsonObject.getString("role"));
    }
}
