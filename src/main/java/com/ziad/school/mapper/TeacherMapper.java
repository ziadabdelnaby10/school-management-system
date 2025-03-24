package com.ziad.school.mapper;

import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.entity.Teacher;
import com.ziad.school.model.request.AddTeacherRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {
    Teacher toEntity(TeacherInfo teacherInfo);

    TeacherInfo toDto(Teacher teacher);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Teacher partialUpdate(TeacherInfo teacherInfo, @MappingTarget Teacher teacher);

    Teacher toEntity(AddTeacherRequest addTeacherRequest);

    AddTeacherRequest toDto1(Teacher teacher);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Teacher partialUpdate(AddTeacherRequest addTeacherRequest, @MappingTarget Teacher teacher);
}