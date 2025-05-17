package com.ziad.school.mapper;

import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.entity.Student;
import com.ziad.school.model.request.student.AddStudentRequest;
import com.ziad.school.model.request.student.UpdateStudentRequest;
import com.ziad.school.model.response.StudentAttendanceResponse;
import com.ziad.school.model.response.StudentClassroomsResponse;
import com.ziad.school.model.response.StudentCoursesResponse;
import com.ziad.school.model.response.StudentParentResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        AttendanceMapper.class, ParentMapper.class, ClassroomMapper.class, CourseMapper.class, SubjectMapper.class
})
public interface StudentMapper {


    Student toEntity(AddStudentRequest addStudentRequest);

    StudentInfo toDto(Student student);

    @Mapping(target = "attendances", source = "attendance")
    StudentAttendanceResponse toAttendanceResponse(Student student);

    @Mapping(target = "parentInfo", source = "parent")
    StudentParentResponse toParentResponse(Student student);

    @Mapping(target = "classroomInfo", source = "classrooms")
    StudentClassroomsResponse toClassroomResponse(Student student);

    @Mapping(target = "courses", source = "courses")
    StudentCoursesResponse toCoursesResponse(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(UpdateStudentRequest updateStudentRequest, @MappingTarget Student student);


}