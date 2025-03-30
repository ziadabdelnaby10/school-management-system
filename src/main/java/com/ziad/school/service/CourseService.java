package com.ziad.school.service;

import com.ziad.school.mapper.CourseMapper;
import com.ziad.school.model.dto.CourseInfo;
import com.ziad.school.model.request.AddCourseRequest;
import com.ziad.school.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseInfo> findAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }

    public CourseInfo addCourse(AddCourseRequest request) {
        var course = courseMapper.toEntity(request);
        return courseMapper.toDto(courseRepository.save(course));
    }

    public void deleteCourse(String name) {
        courseRepository.deleteById(name);
    }
}
