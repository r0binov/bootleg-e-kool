package com.example.bootlegekool.service;

import com.example.bootlegekool.exception.BadRequestException;
import com.example.bootlegekool.exception.StudentNotFoundException;
import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.repository.StudentRepository;
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

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents() {
        // Arrange
        List<Student> students = List.of(new Student("1L", "John"), new Student("2L", "Alice"));
        Mockito.when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertTrue(result.size() == 2);
    }

    @Test
    public void testAddStudent() {
        // Arrange
        Student student = new Student(1L, "John", "john@example.com");
        Mockito.when(studentRepository.selectExistsEmail(student.getEmail())).thenReturn(false);

        // Act
        studentService.addStudent(student);

        // Assert
        Mockito.verify(studentRepository).save(student);
    }

    @Test
    public void testAddStudentWithDuplicateEmail() {
        // Arrange
        Student student = new Student(1L, "John", "john@example.com");
        Mockito.when(studentRepository.selectExistsEmail(student.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(BadRequestException.class, () -> studentService.addStudent(student));
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        Long studentId = 1L;
        Mockito.when(studentRepository.existsById(studentId)).thenReturn(true);

        // Act
        studentService.deleteStudent(studentId);

        // Assert
        Mockito.verify(studentRepository).deleteById(studentId);
    }

    @Test
    public void testDeleteNonExistentStudent() {
        // Arrange
        Long studentId = 1L;
        Mockito.when(studentRepository.existsById(studentId)).thenReturn(false);

        // Act and Assert
        assertThrows(StudentNotFoundException.class, () -> studentService.deleteStudent(studentId));
    }
}
