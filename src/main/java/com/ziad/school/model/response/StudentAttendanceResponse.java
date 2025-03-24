package com.ziad.school.model.response;

import com.ziad.school.model.dto.AttendanceInfo;
import com.ziad.school.model.dto.StudentInfo;

import java.util.List;

public record StudentAttendanceResponse(
        StudentInfo studentInformation,
        List<AttendanceInfo> attendances
) {
}
