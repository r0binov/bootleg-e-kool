package com.example.bootlegekool.service;

import com.example.bootlegekool.exception.SubjectNotFoundException;
import com.example.bootlegekool.models.Subject;
import com.example.bootlegekool.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        // Arrange
        List<Subject> subjects = List.of(new Subject(1L, "Math"), new Subject(2L, "Science"));
        Mockito.when(subjectRepository.findAll()).thenReturn(subjects);

        // Act
        List<Subject> result = subjectService.getAllSubjects();

        // Assert
        assertTrue(result.size() == 2);
    }

    @Test
    public void testSaveSubject() {
        // Arrange
        Subject subject = new Subject(1L, "Math");
        Mockito.when(subjectRepository.save(subject)).thenReturn(subject);

        // Act
        Subject savedSubject = subjectService.saveSubject(subject);

        // Assert
        assertTrue(savedSubject.getSubjectId() == 1L);
    }

    @Test
    public void testGetSubjectById() {
        // Arrange
        Subject subject = new Subject(1L, "Math");
        Mockito.when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        // Act
        Subject result = subjectService.getSubjectById(1L).orElse(null);

        // Assert
        assertTrue(result != null);
    }

    @Test
    public void testGetSubjectByNonExistentId() {
        // Arrange
        Long nonExistentId = 999L;
        Mockito.when(subjectRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(SubjectNotFoundException.class, () -> subjectService.getSubjectByIdOrThrow(nonExistentId));
    }

    @Test
    public void testDeleteSubject() {
        // Arrange
        Long subjectId = 1L;
        Mockito.when(subjectRepository.existsById(subjectId)).thenReturn(true);

        // Act
        subjectService.deleteSubject(subjectId);

        // Assert
        Mockito.verify(subjectRepository).deleteById(subjectId);
    }

    @Test
    public void testDeleteNonExistentSubject() {
        // Arrange
        Long subjectId = 1L;
        Mockito.when(subjectRepository.existsById(subjectId)).thenReturn(false);

        // Act and Assert
        assertThrows(SubjectNotFoundException.class, () -> subjectService.deleteSubject(subjectId));
    }
}
