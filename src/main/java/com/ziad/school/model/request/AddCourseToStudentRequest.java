package com.ziad.school.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddCourseToStudentRequest(
        @NotNull(message = "Must Provide Student Id") @NotBlank(message = "Must Provide Student Id") UUID studentId,
        @NotNull(message = "Must Provide Course Id") @NotBlank(message = "Must Provide Course Id") Long courseId
) {
}
