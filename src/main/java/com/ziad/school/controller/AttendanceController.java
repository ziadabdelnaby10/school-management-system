package com.ziad.school.controller;

import com.ziad.school.model.dto.AttendanceInfo;
import com.ziad.school.model.request.AddAttendanceRequest;
import com.ziad.school.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    public List<AttendanceInfo> getAttendances() {
        return attendanceService.findAll();
    }

    @PostMapping("/add")
    public AttendanceInfo addAttendance(@RequestBody @Valid AddAttendanceRequest request) {
        return attendanceService.addNewAttendance(request);
    }


}
