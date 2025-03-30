package com.ziad.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.ziad.school.model.entity.Classroom}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomInfo implements Serializable {
    private Long id;
    private String year;
    private String section;
    private Boolean isAvailable;
    private String remarks;
}