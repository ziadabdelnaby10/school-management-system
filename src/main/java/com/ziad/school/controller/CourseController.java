package com.ziad.school.controller;

import com.ziad.school.model.dto.CourseInfo;
import com.ziad.school.model.request.AddCourseRequest;
import com.ziad.school.model.response.ApiResponse;
import com.ziad.school.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<CourseInfo>> getAllCourses() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved all courses",
                courseService.findAll()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CourseInfo> addCourse(@RequestBody AddCourseRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully added course",
                courseService.addCourse(request)
        );
    }

    @DeleteMapping("/{courseName}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ApiResponse<Void> deleteCourse(@PathVariable String courseName) {
        courseService.deleteCourse(courseName);
        return new ApiResponse<>(
                HttpStatus.MOVED_PERMANENTLY.value(),
                "Successfully deleted course :" + courseName,
                null
                );
    }
}
