package com.ziad.school.mapper;

import com.ziad.school.model.dto.AttendanceInfo;
import com.ziad.school.model.entity.Attendance;
import com.ziad.school.model.request.AddAttendanceRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttendanceMapper {
    Attendance toEntity(AttendanceInfo attendanceInfo);

    AttendanceInfo toDto(Attendance attendance);

    List<AttendanceInfo> toDto(List<Attendance> attendanceList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attendance partialUpdate(AttendanceInfo attendanceInfo, @MappingTarget Attendance attendance);

//    @Mapping(source = "studentId", target = "student.email")
    Attendance toEntity(AddAttendanceRequest addAttendanceRequest);
}