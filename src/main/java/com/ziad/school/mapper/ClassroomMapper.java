package com.ziad.school.mapper;

import com.ziad.school.model.dto.ClassroomInfo;
import com.ziad.school.model.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClassroomMapper {
    Classroom toEntity(ClassroomInfo classroomInfo);

    ClassroomInfo toDto(Classroom classroom);

}