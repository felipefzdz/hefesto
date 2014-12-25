package org.olid16.infrastructure.dependency_injection;

import com.google.inject.AbstractModule;
import org.olid16.domain.Users;
import org.olid16.infrastructure.repositories.InMemoryUsers;

public class UserServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Users.class).to(InMemoryUsers.class);
    }
}
