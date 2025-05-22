package com.ziad.school.mapper;

import com.ziad.school.model.dto.GradeInfo;
import com.ziad.school.model.entity.Grade;
import com.ziad.school.model.request.AddGradeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GradeMapper {
    GradeInfo toDto(Grade grade);

    Grade toEntity(AddGradeRequest addGradeRequest);
}