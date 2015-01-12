package org.olid16.domain.factories;

import com.google.common.base.Strings;
import org.olid16.domain.values.UserId;
import org.olid16.infrastructure.exceptions.ValidationException;

public class UserIdFactory {

    public UserId create(String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            throw new ValidationException(String.format("%s is mandatory", userId));
        }
        return UserId.create(userId);
    }
}
