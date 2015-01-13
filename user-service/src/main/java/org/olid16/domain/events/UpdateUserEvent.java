package org.olid16.domain.events;

import com.eclipsesource.json.JsonObject;
import org.olid16.domain.entities.User;

public class UpdateUserEvent implements UserEvent {

    private final JsonObject message;

    private UpdateUserEvent(JsonObject message) {
        this.message = message;
    }

    public static UpdateUserEvent createUpdateUserEvent(User user) {
        JsonObject message = new JsonObject().add("id", user.id()).add("name", user.name());
        return new UpdateUserEvent(message);
    }

    @Override
    public String message() {
        return message.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateUserEvent that = (UpdateUserEvent) o;

        if (!message.equals(that.message)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }
}
