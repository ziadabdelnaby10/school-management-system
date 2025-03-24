package com.ziad.school.controller;

import com.ziad.school.model.dto.ParentInfo;
import com.ziad.school.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ParentInfo> getAllParents() {
        return parentService.getAllParents();
    }
}
