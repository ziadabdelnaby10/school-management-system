package com.ziad.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link com.ziad.school.model.entity.Teacher}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherInfo implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String mobile;
    private Date dateOfBirth;
    private Boolean isActive;
    private SubjectInfo teachingSubject;
}