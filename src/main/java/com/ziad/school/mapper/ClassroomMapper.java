package com.ziad.school.mapper;

import com.ziad.school.model.dto.ClassroomInfo;
import com.ziad.school.model.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = StudyYearMapper.class)
public interface ClassroomMapper {
    @Mapping(target = "year.name", source = "year")
    Classroom toEntity(ClassroomInfo classroomInfo);

    @Mapping(target = "year", source = "year.name")
    ClassroomInfo toDto(Classroom classroom);

}