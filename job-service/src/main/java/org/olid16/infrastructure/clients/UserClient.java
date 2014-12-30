package org.olid16.infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.DomainException;
import us.monoid.json.JSONException;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import java.io.IOException;

public class UserClient {
    private final Resty resty;
    private final UserAdapter userAdapter;

    @Inject
    public UserClient(Resty resty, UserAdapter userAdapter) {
        this.resty = resty;
        this.userAdapter = userAdapter;
    }

    public User getBy(UserId userId) {
        String url = String.format("http://localhost:8080/user/%s", userId.id());
        try {
            JSONResource resource = resty.json(url);
            return userAdapter.from(resource);
        } catch (IOException| JSONException e) {
            throw new DomainException(String.format("Problem while connecting to %s", url), e);
        }
    }
}
