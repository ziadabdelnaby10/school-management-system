package com.ziad.school.service;

import com.ziad.school.mapper.ManagerMapper;
import com.ziad.school.model.request.AddManagerRequest;
import com.ziad.school.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerMapper managerMapper;

    public void addManager(AddManagerRequest manager){
        var hashPassword = passwordEncoder.encode(manager.password());
        var newManager = managerMapper.toEntity(manager);
        newManager.setIsActive(Boolean.TRUE);
        newManager.setPassword(hashPassword);
        managerRepository.save(newManager);
    }
}
