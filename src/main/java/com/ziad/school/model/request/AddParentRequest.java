package com.ziad.school.model.request;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.ziad.school.model.entity.Parent}
 */
public record AddParentRequest(
        String firstName,
        String lastName,
        String email,
        Boolean isMale,
        String password,
        String phone,
        String mobile,
        Date dateOfBirth
) implements Serializable {
}