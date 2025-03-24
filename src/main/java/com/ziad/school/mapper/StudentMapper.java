package com.ziad.school.mapper;

import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.entity.Student;
import com.ziad.school.model.request.AddStudentRequest;
import com.ziad.school.model.request.AddStudentToParentRequest;
import com.ziad.school.model.response.StudentAttendanceResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AttendanceMapper.class})
public interface StudentMapper {
    Student toEntity(StudentInfo studentInfo);

    Student toEntity(AddStudentRequest addStudentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentInfo studentInfo, @MappingTarget Student student);

    AddStudentRequest toStudentRequest(Student student);

    StudentInfo toDto(Student student);

    @Mappings(
            value = {
                    @Mapping(target = "attendances", source = "attendance"),
                    @Mapping(target = "studentInformation.id", source = "id"),
                    @Mapping(target = "studentInformation.firstName", source = "firstName"),
                    @Mapping(target = "studentInformation.lastName", source = "lastName"),
                    @Mapping(target = "studentInformation.email", source = "email"),
                    @Mapping(target = "studentInformation.phone", source = "phone"),
                    @Mapping(target = "studentInformation.isActive", source = "isActive"),
                    @Mapping(target = "studentInformation.mobile", source = "mobile")
            }
    )
    StudentAttendanceResponse toAttendanceResponse(Student student);

    Student toEntity(AddStudentToParentRequest addStudentToParentRequest);

}