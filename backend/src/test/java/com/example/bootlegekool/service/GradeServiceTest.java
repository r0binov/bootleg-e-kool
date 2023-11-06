package com.example.bootlegekool.service;

import com.example.bootlegekool.dto.GradeDTO;
import com.example.bootlegekool.dto.UpdateGradeDTO;
import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.models.Subject;
import com.example.bootlegekool.repository.GradeRepository;
import com.example.bootlegekool.repository.StudentRepository;
import com.example.bootlegekool.repository.SubjectRepository;
import com.example.bootlegekool.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GradeServiceTest {
    @InjectMocks
    private GradeService gradeService;

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllGrades() {

        List<Grade> grades = Arrays.asList(new Grade(), new Grade());

        when(gradeRepository.findAll()).thenReturn(grades);

        List<Grade> result = gradeService.getAllGrades();

        assertEquals(2, result.size());
    }


    @Test
    public void testAddGrade() {
        GradeDTO gradeDTO = new GradeDTO(1L, 2L, 85);

        Student student = new Student();
        student.setStudentId(1L);
        Subject subject = new Subject();
        subject.setSubjectId(2L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(subjectRepository.findById(2L)).thenReturn(Optional.of(subject));
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Grade result = gradeService.addGrade(gradeDTO);

        assertNotNull(result);
        assertEquals(1L, result.getStudent().getStudentId());
        assertEquals(2L, result.getSubject().getSubjectId());
        assertEquals(85, result.getGradeValue());
    }

    @Test
    public void testUpdateGrade() {
        UpdateGradeDTO updateGradeDTO = new UpdateGradeDTO();
        updateGradeDTO.setGradeId(1L);
        updateGradeDTO.setGradeValue(90);

        Grade existingGrade = new Grade();
        existingGrade.setGradeId(1L);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(existingGrade));
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Grade result = gradeService.updateGrade(updateGradeDTO);

        assertEquals(90, result.getGradeValue());
        assertEquals(1L, result.getGradeId());
    }


    @Test
    public void testDeleteGrade() {
        Long gradeId = 1L;

        when(gradeRepository.existsById(gradeId)).thenReturn(true);

        String result = gradeService.deleteGrade(gradeId);

        assertEquals("Grade deleted", result);
    }
}