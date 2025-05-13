package com.ziad.school.service;

import com.ziad.school.mapper.GradeMapper;
import com.ziad.school.model.dto.GradeInfo;
import com.ziad.school.model.request.AddGradeRequest;
import com.ziad.school.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public List<GradeInfo> getAllGrades() {
        return gradeRepository.findAll().stream().map(gradeMapper::toDto).toList();
    }

    public GradeInfo addGrade(AddGradeRequest request) {
        var grade = gradeMapper.toEntity(request);
        return gradeMapper.toDto(gradeRepository.save(grade));
    }

    public void deleteGrade(Long gradeId) {
        gradeRepository.deleteById(gradeId);
    }
}
