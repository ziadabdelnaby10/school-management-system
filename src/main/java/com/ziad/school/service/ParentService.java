package com.ziad.school.service;

import com.ziad.school.mapper.ParentMapper;
import com.ziad.school.model.dto.ParentInfo;
import com.ziad.school.model.request.AddParentRequest;
import com.ziad.school.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    public List<ParentInfo> getAllParents() {
        return parentRepository.findAll().stream().map(parentMapper::toDto).toList();
    }

    public Page<ParentInfo> getAllParents(Pageable pageable) {
        return parentRepository.findAll(pageable).map(parentMapper::toDto);
    }

    public ParentInfo getParentById(UUID id) {
        return parentMapper.toDto(parentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public ParentInfo createParent(AddParentRequest request) {
        var newParent = parentMapper.toEntity(request);
        newParent.setIsActive(true);
        return parentMapper.toDto(parentRepository.save(newParent));
    }

    public void deleteParent(UUID parentId) {
        parentRepository.deleteById(parentId);
    }
}
