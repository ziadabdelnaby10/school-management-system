package com.ziad.school.mapper;

import com.ziad.school.model.dto.ParentInfo;
import com.ziad.school.model.entity.Parent;
import com.ziad.school.model.request.AddParentRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParentMapper {
    Parent toEntity(ParentInfo parentInfo);

    ParentInfo toDto(Parent parent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Parent partialUpdate(ParentInfo parentInfo, @MappingTarget Parent parent);

    Parent toEntity(AddParentRequest addParentRequest);

    AddParentRequest toDto1(Parent parent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Parent partialUpdate(AddParentRequest addParentRequest, @MappingTarget Parent parent);
}