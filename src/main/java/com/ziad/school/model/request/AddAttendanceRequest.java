package com.ziad.school.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;


public record AddAttendanceRequest(
        @NotNull(message = "Must Provide Student Id") @NotBlank(message = "Must Provide Student Id") UUID studentId,
        @NotNull(message = "Must state if the student is attendant or not") Boolean isAttendance,
        String remark
) implements Serializable {
}