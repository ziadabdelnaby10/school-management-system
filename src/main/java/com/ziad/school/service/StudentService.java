package com.ziad.school.service;

import com.ziad.school.mapper.StudentMapper;
import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.entity.Student;
import com.ziad.school.model.request.AddClassroomToStudentRequest;
import com.ziad.school.model.request.AddCourseToStudentRequest;
import com.ziad.school.model.request.AddStudentRequest;
import com.ziad.school.model.request.AddStudentToParentRequest;
import com.ziad.school.model.response.StudentAttendanceResponse;
import com.ziad.school.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ParentRepository parentRepository;
    private final AttendanceRepository attendanceRepository;
    private final ClassroomRepository classroomRepository;
    private final CourseRepository courseRepository;

    //    @Cacheable(value = "student")
    public List<StudentInfo> getAllStudents() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).toList();
    }

    public Page<StudentInfo> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    //    @Cacheable(value = "student" , key = "#id")
    public StudentInfo getStudentById(UUID id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public StudentInfo createStudent(AddStudentRequest student) {
        var newStudent = studentMapper.toEntity(student);
        newStudent.setIsActive(Boolean.TRUE);
        return studentMapper.toDto(studentRepository.save(newStudent));
    }

    public StudentAttendanceResponse getStudentAttendances(UUID studentId) {
        Student student = studentRepository.findAllWithAttendance(studentId);
        return studentMapper.toAttendanceResponse(student);
    }

    public StudentInfo addParentToStudent(AddStudentToParentRequest request) {
        //getReferenceById doesn't throw exception if the id is not found either produce null
        var parent = parentRepository.getReferenceById(request.parentId());
        var student = studentRepository.findById(request.id()).orElseThrow(EntityNotFoundException::new);
        student.setParent(parent);
        return studentMapper.toDto(studentRepository.save(student));
    }

    public StudentInfo addStudentToClassroom(AddClassroomToStudentRequest request) {
        var classroom = classroomRepository.getReferenceById(request.classroomId());
        var student = studentRepository.findById(request.studentId()).orElseThrow(EntityNotFoundException::new);
        student.getClassrooms().add(classroom);
        return studentMapper.toDto(studentRepository.save(student));
    }

    public StudentInfo addStudentToCourse(AddCourseToStudentRequest request) {
        var course = courseRepository.getReferenceById(request.courseId());
        var student = studentRepository.findById(request.studentId()).orElseThrow(EntityNotFoundException::new);
        student.getCourses().add(course);
        return studentMapper.toDto(studentRepository.save(student));
    }
}