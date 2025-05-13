package com.ziad.school.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public record AddGradeRequest(
        @NotNull(message = "Must Provide a name") @NotBlank(message = "Must Provide a name") String name,
        String description) implements Serializable { }