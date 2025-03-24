package com.ziad.school.controller;

import com.ziad.school.model.dto.TeacherInfo;
import com.ziad.school.model.request.AddTeacherRequest;
import com.ziad.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherInfo> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/add")
    public TeacherInfo addTeacher(@RequestBody AddTeacherRequest request) {
        return teacherService.addTeacher(request);
    }
}
