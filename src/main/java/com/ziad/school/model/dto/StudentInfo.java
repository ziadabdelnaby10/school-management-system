package com.ziad.school.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.util.*;

/**
 * DTO for {@link com.ziad.school.model.entity.Student}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean isMale;
    private String password;
    private String phone;
    private String mobile;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy/MM/dd")
    private Date dateOfBirth;
    private Boolean isActive;
    private StudyYearInfo currentYear;
}