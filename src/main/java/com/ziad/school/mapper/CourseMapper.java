package com.ziad.school.mapper;

import com.ziad.school.model.entity.Course;
import com.ziad.school.model.request.AddCourseRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    Course toEntity(AddCourseRequest addCourseRequest);

    AddCourseRequest toDto(Course course);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Course partialUpdate(AddCourseRequest addCourseRequest, @MappingTarget Course course);
}