package org.olid16.actions;

import org.olid16.domain.entities.Job;
import org.olid16.infrastructure.exceptions.AuthorizationException;
import org.olid16.domain.services.UserService;
import org.olid16.infrastructure.rest.JsonEntity;

public class CreateJob {


    private final UserService userService;

    public CreateJob(UserService userService) {
        this.userService = userService;
    }

    public Job with(JsonEntity jsonEntity) {
        if (userService.isEmployer(jsonEntity)){
            return new Job();
        }
        throw new AuthorizationException("Only employers can create jobs");
    }
}
