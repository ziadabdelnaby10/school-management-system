package com.ziad.school.controller;

import com.ziad.school.model.dto.GradeInfo;
import com.ziad.school.model.request.AddGradeRequest;
import com.ziad.school.model.response.ApiResponse;
import com.ziad.school.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<GradeInfo>> getAllGrades() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrived all grades",
                gradeService.getAllGrades()
        );
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<GradeInfo> addGrade(@RequestBody @Valid AddGradeRequest request) {
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Successfully added grade",
                gradeService.addGrade(request)
        );
    }

    @DeleteMapping("/{gradeId}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ApiResponse<Void> deleteGrade(@PathVariable Long gradeId) {
        gradeService.deleteGrade(gradeId);
        return new ApiResponse<>(
                HttpStatus.MOVED_PERMANENTLY.value(),
                "Successfully Deleted grade with id: " + gradeId,
                null
        );
    }
}