package com.ziad.school.controller;

import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.request.teacher.AddClassroomToTeacherRequest;
import com.ziad.school.model.response.ApiResponse;
import com.ziad.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<TeacherInfo>> getAllTeachers() {
        return new ApiResponse<>(HttpStatus.OK.value(), "Successfully retrived All Teachers", teacherService.getAllTeachers());
    }

    @PostMapping("/add/classroom")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TeacherInfo> addClassroomToTeacher(@RequestBody AddClassroomToTeacherRequest request) {
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Successfully added Classroom", teacherService.addClassroomToTeacher(request));
    }

    @DeleteMapping("/{teacherId}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ApiResponse<Void> deleteTeacher(@PathVariable UUID teacherId) {
        teacherService.deleteTeacher(teacherId);
        return new ApiResponse<>(HttpStatus.MOVED_PERMANENTLY.value(), "Deleted Teacher with id " + teacherId , null);
    }
}
