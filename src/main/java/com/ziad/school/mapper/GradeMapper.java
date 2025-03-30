package com.ziad.school.mapper;

import com.ziad.school.model.dto.GradeInfo;
import com.ziad.school.model.entity.Grade;
import com.ziad.school.service.AddGradeRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {
    GradeInfo toDto(Grade grade);

    Grade toEntity(AddGradeRequest addGradeRequest);
}