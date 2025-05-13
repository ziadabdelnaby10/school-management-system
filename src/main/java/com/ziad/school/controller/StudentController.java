package com.ziad.school.controller;

import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.request.student.*;
import com.ziad.school.model.response.*;
import com.ziad.school.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ApiResponse<List<StudentInfo>> getAllStudents() {
//        return new ApiResponse<>(
//                HttpStatus.OK.value(),
//                "Successfully retrieved all students",
//                studentService.getAllStudents()
//        );
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentInfo> getAllStudents(Pageable page) {
        return studentService.getAllStudents(page);
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
                "Successfully fetched attendances of the student with ID " + studentId,
                studentService.getStudentAttendances(studentId)
        );
    }

    @GetMapping("/{studentId}/parent")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<StudentParentResponse> getStudentParent(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully fetched parent of the student with ID " + studentId,
                studentService.getStudentParent(studentId)
        );
    }

    @GetMapping("/{studentId}/classroom")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<StudentClassroomsResponse> getStudentClassroom(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully fetched classrooms of the student with ID " + studentId,
                studentService.getStudentClassrooms(studentId)
        );
    }

    @GetMapping("/{studentId}/course")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<StudentCoursesResponse> getStudentCourses(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully fetched courses of the student with ID " + studentId,
                studentService.getStudentCourses(studentId)
        );
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UUID> deleteStudent(@PathVariable @Valid @NotNull @NotBlank UUID studentId) {
        studentService.deleteStudent(studentId);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Deleted Successfully",
                studentId
        );
    }

    @PutMapping("/{studentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<StudentInfo> updateStudent(@PathVariable @NotNull(message = "Must Provide The Id")
                                                  @NotBlank(message = "Must Provide The Id")
                                                  UUID studentId, UpdateStudentRequest updateStudentRequest) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Updated Student with id " + studentId,
                studentService.updateStudent(studentId, updateStudentRequest)
        );
    }



    //this should be modified to /add/{studentId}/parent
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

//https://medium.com/@burakkocakeu/in-spring-data-jpa-onetomany-what-are-these-fields-mappedby-fetch-cascade-and-orphanremoval-2655f4027c4f#:~:text=orphanRemoval%20is%20specifically%20designed%20to,are%20referenced%20by%20other%20entities.