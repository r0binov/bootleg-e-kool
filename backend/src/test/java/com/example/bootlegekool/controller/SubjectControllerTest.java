package com.example.bootlegekool.controller;

import com.example.bootlegekool.models.Subject;
import com.example.bootlegekool.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        // Arrange
        List<Subject> subjects = Arrays.asList(new Subject(1L, "Math"), new Subject(2L, "Science"));
        Mockito.when(subjectService.getAllSubjects()).thenReturn(subjects);

        // Act
        List<Subject> result = subjectController.getAllSubjects();

        // Assert
        assertEquals(subjects, result);
    }

    @Test
    public void testCreateSubject() {
        // Arrange
        Subject subject = new Subject(1L, "Math");
        Mockito.when(subjectService.saveSubject(subject)).thenReturn(subject);

        // Act
        ResponseEntity<Subject> response = subjectController.createSubject(subject);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(subject, response.getBody());
    }

    @Test
    public void testUpdateSubject() {
        // Arrange
        Long subjectId = 1L;
        Subject updatedSubject = new Subject(subjectId, "Updated Math");

        Mockito.when(subjectService.getSubjectById(subjectId)).thenReturn(Optional.of(new Subject(subjectId, "Math")));
        Mockito.when(subjectService.saveSubject(updatedSubject)).thenReturn(updatedSubject);

        // Act
        ResponseEntity<Subject> response = subjectController.updateSubject(subjectId, updatedSubject);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSubject, response.getBody());
    }

    @Test
    public void testDeleteSubject() {
        // Arrange
        Long subjectId = 1L;

        Mockito.when(subjectService.getSubjectById(subjectId)).thenReturn(Optional.of(new Subject(subjectId, "Math")));

        // Act
        ResponseEntity<Void> response = subjectController.deleteSubject(subjectId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
