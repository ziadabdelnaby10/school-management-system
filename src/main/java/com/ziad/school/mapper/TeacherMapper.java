package com.ziad.school.mapper;

import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.entity.Classroom;
import com.ziad.school.model.entity.Teacher;
import com.ziad.school.model.request.teacher.AddTeacherRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {SubjectMapper.class}
)
public interface TeacherMapper {
    Teacher toEntity(TeacherInfo teacherInfo);

    TeacherInfo toDto(Teacher teacher);

    @Mapping(target = "teachingSubject.name", source = "teachingSubject")
    Teacher toEntity(AddTeacherRequest addTeacherRequest);

//    @Mapping(target = "classroomNames", expression = "java(classroomsToClassroomNames(teacher.getClassrooms()))")

    default Set<String> classroomsToClassroomNames(Set<Classroom> classrooms) {
        return classrooms.stream().map(Classroom::getName).collect(Collectors.toSet());
    }

//    default Subject map(String value){
//        return new Subject(value);
//    }
}