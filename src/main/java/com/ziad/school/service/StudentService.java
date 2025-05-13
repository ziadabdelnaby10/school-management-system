package com.ziad.school.service;

import com.ziad.school.mapper.StudentMapper;
import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.entity.Student;
import com.ziad.school.model.request.student.*;
import com.ziad.school.model.response.StudentAttendanceResponse;
import com.ziad.school.model.response.StudentClassroomsResponse;
import com.ziad.school.model.response.StudentCoursesResponse;
import com.ziad.school.model.response.StudentParentResponse;
import com.ziad.school.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final PasswordEncoder passwordEncoder;

    public Page<StudentInfo> getAllStudents(Pageable pageable) {
        var students = studentRepository.findAll(pageable);
        var mappedStudents = students.map(studentMapper::toDto);
        return mappedStudents;
    }

    //    @Cacheable(value = "student" , key = "#id")
    public StudentInfo getStudentById(UUID id) {
        return studentMapper.toDto(studentRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void createStudent(AddStudentRequest student) {
        var hashPassword = passwordEncoder.encode(student.password());
        var newStudent = studentMapper.toEntity(student);
        newStudent.setPassword(hashPassword);
        newStudent.setIsActive(Boolean.TRUE);
        studentRepository.save(newStudent);
    }

    public StudentAttendanceResponse getStudentAttendances(UUID studentId) {
        Student student = studentRepository.findStudentWithAttendances(studentId);
        return studentMapper.toAttendanceResponse(student);
    }

    public StudentParentResponse getStudentParent(UUID studentId) {
        Student student = studentRepository.findStudentWithParent(studentId);
        return studentMapper.toParentResponse(student);
    }

    public StudentClassroomsResponse getStudentClassrooms(UUID studentId) {
        Student student = studentRepository.findStudentWithClassrooms(studentId);
        return studentMapper.toClassroomResponse(student);
    }

    public StudentCoursesResponse getStudentCourses(UUID studentId) {
        Student student = studentRepository.findStudentWithCourses(studentId);
        return studentMapper.toCoursesResponse(student);
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

    public void deleteStudent(UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    public StudentInfo updateStudent(UUID studentId, UpdateStudentRequest request) {
        var oldStudent = studentRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        var newStudent = studentMapper.partialUpdate(request, oldStudent);
        return studentMapper.toDto(studentRepository.save(newStudent));
    }
}