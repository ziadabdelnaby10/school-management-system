package com.ziad.school.mapper;

import com.ziad.school.model.entity.Manager;
import com.ziad.school.model.request.AddManagerRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerMapper {
    Manager toEntity(AddManagerRequest addManagerRequest);

    AddManagerRequest toDto(Manager manager);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Manager partialUpdate(AddManagerRequest addManagerRequest, @MappingTarget Manager manager);
}