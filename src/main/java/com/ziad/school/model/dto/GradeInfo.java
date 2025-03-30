package com.ziad.school.model.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.ziad.school.model.entity.Grade}
 */
public record GradeInfo(Long id, String name, String description) implements Serializable {
}