package com.example.bootlegekool.service;

import com.example.bootlegekool.exception.BadRequestException;
import com.example.bootlegekool.exception.StudentNotFoundException;
import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //Finding all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //Adding new Student
    public void addStudent(Student student) {
        Boolean existsEmail = studentRepository.selectExistsEmail(student.getEmail());

        if (existsEmail) {
            throw new BadRequestException("Email " + student.getEmail() + " taken");
        }

        studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        Long id = student.getStudentId();

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());

        studentRepository.save(existingStudent);

        return student;
    }

    //Delete Student
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Student with id " + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);

    }
}
