package com.ziad.school.model.request.student;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.ziad.school.model.entity.Student}
 */
public record UpdateStudentRequest(
        String firstName,
        String lastName,
        String email,
        Boolean isMale,
        String password,
        String phone,
        String mobile,
        Date dateOfBirth,
        Boolean isActive,
        String currentYear
) implements Serializable {
}