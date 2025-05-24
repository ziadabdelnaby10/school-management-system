package com.ziad.school.controller.mvc;

import com.ziad.school.model.dto.StudentInfo;
import com.ziad.school.model.entity.Student;
import com.ziad.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
@Slf4j
public class StudentMVCController {

    private final StudentService studentService;

    @GetMapping
    public String getStudents(Pageable page, Model model) {
        var studentsInfo = studentService.getAllStudents(page);
        log.info("studentsInfo: {}", studentsInfo.getContent());
        model.addAttribute("studentsInfo", studentsInfo.getContent());
        model.addAttribute("total", studentsInfo.getTotalElements());
        return "students";
    }
}
