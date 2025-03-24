package com.ziad.school.mapper;

import com.ziad.school.model.dto.ExamResultInfo;
import com.ziad.school.model.entity.ExamResult;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExamResultMapper {
    ExamResult toEntity(ExamResultInfo examResultInfo);

    ExamResultInfo toDto(ExamResult examResult);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ExamResult partialUpdate(ExamResultInfo examResultInfo, @MappingTarget ExamResult examResult);
}