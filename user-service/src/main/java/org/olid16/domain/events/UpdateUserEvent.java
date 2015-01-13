package org.olid16.domain.events;

import org.olid16.domain.entities.User;

public class UpdateUserEvent implements UserEvent {
    private final User user;

    private UpdateUserEvent(User user) {
        this.user = user;
    }

    public static UpdateUserEvent createUpdateUserEvent(User user) {
        return new UpdateUserEvent(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateUserEvent that = (UpdateUserEvent) o;

        if (!user.equals(that.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
