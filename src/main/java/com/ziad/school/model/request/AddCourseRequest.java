package com.ziad.school.model.request;

import java.io.Serializable;

/**
 * DTO for {@link com.ziad.school.model.entity.Course}
 */
public record AddCourseRequest(String name, String description) implements Serializable {
}