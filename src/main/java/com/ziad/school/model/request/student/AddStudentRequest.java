package com.ziad.school.model.request.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziad.school.model.base.StudyYear;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.ziad.school.model.entity.Student}
 */
public record AddStudentRequest(
        @NotNull(message = "Must Enter The First Name")
        @NotEmpty(message = "The First Name Cannot be empty")
        @NotBlank(message = "The First Name Cannot be blank")
        @Length(message = "Enter A valid Length", max = 45)
        String firstName,
        @NotNull(message = "Must Enter The Last Name")
        @NotEmpty(message = "The Last Name Cannot be empty")
        @NotBlank(message = "The Last Name Cannot be blank")
        @Length(message = "Enter A valid Length", max = 45)
        String lastName,
        @NotNull(message = "Must Enter The Email")
        @NotEmpty(message = "The Email Cannot be empty")
        @NotBlank(message = "The Email Cannot be blank")
//        @Pattern(regexp = ".*")
        @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
        String email,
        @NotNull(message = "Must Enter The Password")
        @NotEmpty(message = "The Password Cannot be empty")
        @NotBlank(message = "The Password Cannot be blank")
        String password,
        String phone,
        Boolean isMale,
        String mobile,
        @JsonFormat(pattern = "dd-MM-yyyy")
        Date dateOfBirth,
        StudyYear currentYear) implements Serializable {
}