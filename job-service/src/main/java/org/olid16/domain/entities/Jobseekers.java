package org.olid16.domain.entities;

import org.olid16.domain.values.UserId;

import java.util.HashSet;
import java.util.Set;

public class Jobseekers {

    private Set<UserId> jobseekers = new HashSet<>();

    public static Jobseekers create() {
        return new Jobseekers();
    }

    public void add(UserId jobseekerId) {
        jobseekers.add(jobseekerId);    
    }

    public boolean contains(UserId userId) {
        return jobseekers.contains(userId);
    }
}
