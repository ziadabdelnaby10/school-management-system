package com.ziad.school.mapper;

import com.ziad.school.model.dto.SubjectInfo;
import com.ziad.school.model.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper {
    SubjectInfo toDto(Subject subject);

    Subject toEntity(SubjectInfo subjectInfo);

    default Subject mapToSubject(String subject) {
        return new Subject(subject);
    }

    default SubjectInfo mapToSubjectInfo(String subject) {
        return new SubjectInfo(subject);
    }
}