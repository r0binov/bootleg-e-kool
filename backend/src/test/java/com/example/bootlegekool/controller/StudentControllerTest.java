package com.example.bootlegekool.controller;


import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.service.StudentService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents() {
        // Arrange
        List<Student> students = Arrays.asList(new Student("1L", "John"), new Student("2L", "Alice"));
        Mockito.when(studentService.getAllStudents()).thenReturn(students);

        // Act
        List<Student> result = studentController.getAllStudents();

        // Assert
        assertEquals(students, result);
    }

    @Test
    public void testAddStudent() {
        // Arrange
        Student student = new Student("1L", "John");

        // Act
        studentController.addStudent(student);

        // Assert
        Mockito.verify(studentService).addStudent(student);
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        Long studentId = 1L;

        // Act
        studentController.deleteStudent(studentId);

        // Assert
        Mockito.verify(studentService).deleteStudent(studentId);
    }
}