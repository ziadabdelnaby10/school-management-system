package com.ziad.school.model.request;

import jakarta.validation.constraints.*;

public record UserLoginRequest(
        @NotNull(message = "Must Enter The Email")
        @NotEmpty(message = "The Email Cannot be empty")
        @NotBlank(message = "The Email Cannot be blank")
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
        String email ,
        @NotNull(message = "Must Enter The Password")
        @NotEmpty(message = "The Password Cannot be empty")
        @NotBlank(message = "The Password Cannot be blank")
        String password
) {
}
