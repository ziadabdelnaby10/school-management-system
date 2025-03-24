package com.ziad.school.model.request;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ziad.school.model.entity.Student}
 */
public record AddStudentToParentRequest(
        @NotNull(message = "Must Provide The Id") UUID id,
        @NotNull(message = "Must Provide The Id") UUID parentId
) implements Serializable {
}