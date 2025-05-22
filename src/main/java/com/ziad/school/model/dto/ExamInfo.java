package com.ziad.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.ziad.school.model.entity.Exam}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamInfo implements Serializable {
    private Long id;
    private String name;
    private Date start_date;
}