package com.ziad.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.ziad.school.model.entity.StudyYear}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyYearInfo implements Serializable {
    private String name;
}