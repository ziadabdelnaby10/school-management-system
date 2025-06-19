package com.ziad.school.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ziad.school.model.base.StudyYear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    @JsonIgnore
    private String password;
    private String phone;
    private String mobile;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy/MM/dd")
    private Date dateOfBirth;
    private Boolean isActive;
    private StudyYear currentYear;
}