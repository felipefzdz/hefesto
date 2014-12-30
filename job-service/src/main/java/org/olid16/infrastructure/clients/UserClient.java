package org.olid16.infrastructure.clients;

import com.google.inject.Inject;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.DomainException;
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
        String url = String.format("http://localhost:4567/user/%s", userId.id());
        try {
            return userAdapter.from(resty.json(url));
        } catch (IOException e) {
            throw new DomainException(String.format("Unable to connect to %s", url), e);
        }
    }
}
