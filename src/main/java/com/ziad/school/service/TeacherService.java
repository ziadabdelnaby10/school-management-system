package com.ziad.school.service;

import com.ziad.school.mapper.TeacherMapper;
import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.request.teacher.AddClassroomToTeacherRequest;
import com.ziad.school.model.request.teacher.AddTeacherRequest;
import com.ziad.school.repository.ClassroomRepository;
import com.ziad.school.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final ClassroomRepository classroomRepository;

    public List<TeacherInfo> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).toList();
    }

    public void addTeacher(AddTeacherRequest request) {
        var requestTeacher = teacherMapper.toEntity(request);
        requestTeacher.setIsActive(true);
        teacherRepository.save(requestTeacher);
    }

    public TeacherInfo addClassroomToTeacher(AddClassroomToTeacherRequest request){
        var teacher = teacherRepository.findById(request.id()).orElseThrow(EntityNotFoundException::new);
        var classroom = classroomRepository.getReferenceById(request.classroom());
        teacher.getClassrooms().add(classroom);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    public void deleteTeacher(UUID teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
