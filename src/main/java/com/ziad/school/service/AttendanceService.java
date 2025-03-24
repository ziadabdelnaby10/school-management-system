package com.ziad.school.service;

import com.ziad.school.mapper.AttendanceMapper;
import com.ziad.school.model.dto.AttendanceInfo;
import com.ziad.school.model.entity.Attendance;
import com.ziad.school.model.entity.Student;
import com.ziad.school.model.request.AddAttendanceRequest;
import com.ziad.school.repository.AttendanceRepository;
import com.ziad.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentRepository studentRepository;

    public List<AttendanceInfo> findAll() {
        return attendanceRepository.findAll().stream().map(attendanceMapper::toDto).toList();
    }

    public AttendanceInfo findById(Long id) {
        return attendanceRepository.findById(id).map(attendanceMapper::toDto).orElse(null);
    }

    @Transactional
    public AttendanceInfo addNewAttendance(AddAttendanceRequest request) {
        Attendance attendance = attendanceMapper.toEntity(request);
        Student student = studentRepository.getReferenceById(request.studentId());
        attendance.setStudent(student);
        return attendanceMapper.toDto(attendanceRepository.save(attendance));
    }
}
