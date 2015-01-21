package org.olid16.domain.collections;

import org.olid16.domain.entities.Resume;

import java.util.Optional;

public interface Resumes {
    Resume add(Resume resume);

    Optional<Resume> findById(String id);
}
