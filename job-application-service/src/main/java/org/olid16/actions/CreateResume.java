package org.olid16.actions;

import com.google.inject.Inject;
import org.olid16.domain.collections.Resumes;
import org.olid16.domain.entities.Resume;
import org.olid16.domain.values.User;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.clients.UserClient;
import org.olid16.infrastructure.exceptions.AuthorizationException;

import java.util.Optional;

public class CreateResume {

    private final Resumes resumes;
    private final UserClient userClient;

    @Inject
    public CreateResume(Resumes resumes, UserClient userClient) {
        this.resumes = resumes;
        this.userClient = userClient;
    }

    public Resume with(Resume resume) {
        Optional<User> user = userClient.create(UserId.create(resume.userId()));
        if (user.isPresent() && user.get().isJobseeker()){
            return resumes.add(resume);
        }
        throw new AuthorizationException("Only jobseekers can create resumes");
    }
}
