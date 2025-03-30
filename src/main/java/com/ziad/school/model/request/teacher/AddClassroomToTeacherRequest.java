package com.ziad.school.model.request.teacher;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ziad.school.model.entity.Teacher}
 */
public record AddClassroomToTeacherRequest(UUID id, String classroom) implements Serializable {
}