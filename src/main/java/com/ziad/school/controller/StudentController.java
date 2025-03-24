package com.ziad.school.controller;

import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.request.AddClassroomToStudentRequest;
import com.ziad.school.model.request.AddCourseToStudentRequest;
import com.ziad.school.model.request.AddStudentRequest;
import com.ziad.school.model.request.AddStudentToParentRequest;
import com.ziad.school.model.response.ApiResponse;
import com.ziad.school.model.response.StudentAttendanceResponse;
import com.ziad.school.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<StudentInfo>> getAllStudents() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved all students",
                studentService.getAllStudents()
        );
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentInfo> getAllStudents(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber
    ) {
        return studentService.getAllStudents(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, "email")));
    }

    @GetMapping("/{studentId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<StudentInfo> getStudentById(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        return new ApiResponse<>(HttpStatus.OK.value(),
                "Successfully retrieved The Student with ID " + studentId,
                studentService.getStudentById(studentId)
        );
    }

    @GetMapping("/{studentId}/attendance")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<StudentAttendanceResponse> getStudentAttendances(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrived attendances of the student with ID " + studentId,
                studentService.getStudentAttendances(studentId)
        );
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentInfo> addStudent(@RequestBody @Valid AddStudentRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully add student",
                studentService.createStudent(request)
        );
    }

    @PostMapping("/add/parent")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentInfo> addParentToStudent(@RequestBody @Valid AddStudentToParentRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully add parent to student",
                studentService.addParentToStudent(request)
        );
    }

    @PostMapping("/add/classroom")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentInfo> addStudentToClassroom(@RequestBody @Valid AddClassroomToStudentRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully add student to classroom",
                studentService.addStudentToClassroom(request)
        );
    }

    @PostMapping("/add/course")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentInfo> addStudentToCourse(@RequestBody @Valid AddCourseToStudentRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully add student to course",
                studentService.addStudentToCourse(request)
        );
    }
}