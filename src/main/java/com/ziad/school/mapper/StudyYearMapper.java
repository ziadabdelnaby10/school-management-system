package com.ziad.school.mapper;

import com.ziad.school.model.dto.StudyYearInfo;
import com.ziad.school.model.entity.StudyYear;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudyYearMapper {

    StudyYearInfo toDto(StudyYear studyYear);

    default StudyYear toEntity(String studyYearInfo) {
        return new StudyYear(studyYearInfo);
    }

    default StudyYearInfo toDto(String studyYearInfo) {
        return new StudyYearInfo(studyYearInfo);
    }
}