package com.ziad.school.service;

import com.ziad.school.mapper.TeacherMapper;
import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.request.AddTeacherRequest;
import com.ziad.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherInfo> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).toList();
    }

    public TeacherInfo addTeacher(AddTeacherRequest request) {
        var requestTeacher = teacherMapper.toEntity(request);
        return teacherMapper.toDto(teacherRepository.save(requestTeacher));
    }
}
