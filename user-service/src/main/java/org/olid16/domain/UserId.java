package org.olid16.domain;

public class UserId {
    
    private final Integer id;

    public UserId(Integer id) {
        this.id = id;
    }

    public Integer id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        if (!id.equals(userId.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
