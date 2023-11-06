package com.example.bootlegekool.service;

import com.example.bootlegekool.dto.GradeDTO;
import com.example.bootlegekool.dto.UpdateGradeDTO;
import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.models.Subject;
import com.example.bootlegekool.repository.GradeRepository;
import com.example.bootlegekool.repository.StudentRepository;
import com.example.bootlegekool.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public GradeService(
            GradeRepository gradeRepository,
            StudentRepository studentRepository,
            SubjectRepository subjectRepository
    ) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade addGrade(GradeDTO gradeDTO) {
        Student student = studentRepository.findById(gradeDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Subject subject = subjectRepository.findById(gradeDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setGradeValue(gradeDTO.getGradeValue());

        return gradeRepository.save(grade);
    }

    public Grade updateGrade(UpdateGradeDTO updateGradeDTO) {
        Long gradeId = updateGradeDTO.getGradeId();
        int newGradeValue = updateGradeDTO.getGradeValue();


        Grade existingGrade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        existingGrade.setGradeValue(newGradeValue);

        return gradeRepository.save(existingGrade);
    }

    public String deleteGrade(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return "Grade deleted";
        }

        return "Grade with ID " + id + " not found.";
    }
}
