package com.ziad.school.mapper;

import com.ziad.school.model.dto.ExamInfo;
import com.ziad.school.model.entity.Exam;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExamMapper {
    Exam toEntity(ExamInfo examInfo);

    ExamInfo toDto(Exam exam);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Exam partialUpdate(ExamInfo examInfo, @MappingTarget Exam exam);
}