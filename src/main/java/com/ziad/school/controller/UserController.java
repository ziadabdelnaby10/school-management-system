package com.ziad.school.controller;

import com.ziad.school.model.request.AddManagerRequest;
import com.ziad.school.model.request.AddParentRequest;
import com.ziad.school.model.request.UserLoginRequest;
import com.ziad.school.model.request.student.AddStudentRequest;
import com.ziad.school.model.request.teacher.AddTeacherRequest;
import com.ziad.school.model.response.AuthenticationResponse;
import com.ziad.school.service.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final StudentService studentService;
    private final ManagerService managerService;
    private final ParentService parentService;
    private final TeacherService teacherService;

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody @Valid AddStudentRequest request) {
        try {
            studentService.createStudent(request);
            return new ResponseEntity<>("Student Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/manager")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody @Valid AddManagerRequest manager) {
        try {
            managerService.addManager(manager);
            return new ResponseEntity<>("Manager Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody @Valid AddTeacherRequest request) {
        try {
            teacherService.addTeacher(request);
            return new ResponseEntity<>("Teacher Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/parent")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody @Valid AddParentRequest request) {
        try {
            parentService.createParent(request);
            return new ResponseEntity<>("Parent Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
