package com.ziad.school.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziad.school.model.dto.ClassroomInfo;
import com.ziad.school.model.dto.StudentInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record StudentClassroomsResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Boolean isMale,
        String password,
        String phone,
        String mobile,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        Date dateOfBirth,
        Boolean isActive,
        List<ClassroomInfo> classroomInfo
) {
}
