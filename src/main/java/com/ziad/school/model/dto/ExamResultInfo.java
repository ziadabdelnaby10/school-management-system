package com.ziad.school.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.ziad.school.model.entity.ExamResult}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultInfo implements Serializable {
    private Long id;
    private String marks;
}