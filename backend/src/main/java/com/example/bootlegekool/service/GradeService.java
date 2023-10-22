package com.example.bootlegekool.service;

import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public String updateGrade(Long id, Grade updatedGrade) {
        if (gradeRepository.existsById(id)) {
            updatedGrade.setGradeId(id);
            gradeRepository.save(updatedGrade);

            return "Grade with ID " + id + " updated successfully.";
        }

        return "Grade with ID " + id + " not found. Update failed.";
    }

    public String deleteGrade(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return "Grade deleted";
        }

        return "Grade with ID " + id + " not found.";
    }
}
