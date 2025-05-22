package com.ziad.school.service;

import com.ziad.school.mapper.ManagerMapper;
import com.ziad.school.model.request.AddManagerRequest;
import com.ziad.school.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public void addManager(AddManagerRequest manager){
        var newManager = managerMapper.toEntity(manager);
        managerRepository.save(newManager);
    }
}
