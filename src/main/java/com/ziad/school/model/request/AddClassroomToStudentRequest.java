package com.ziad.school.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddClassroomToStudentRequest(
        @NotNull(message = "Must Provide Student Id") @NotBlank(message = "Must Provide Student Id") UUID studentId,
        @NotNull(message = "Must Provide Classroom Id") @NotBlank(message = "Must Provide Classroom Id") Long classroomId
) {
}
