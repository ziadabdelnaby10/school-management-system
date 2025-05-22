package com.ziad.school.controller.rest;

import com.ziad.school.model.dto.ParentInfo;
import com.ziad.school.model.response.ApiResponse;
import com.ziad.school.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<ParentInfo>> getAllParents() {
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully fetched all parents",
                parentService.getAllParents()
        );
    }

    @DeleteMapping("/{parentId}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ApiResponse<Void> deleteParent(@PathVariable UUID parentId) {
        parentService.deleteParent(parentId);
        return new ApiResponse<>(
                HttpStatus.MOVED_PERMANENTLY.value(),
                "Successfully deleted parent with id: " + parentId,
                null
        );
    }
}
