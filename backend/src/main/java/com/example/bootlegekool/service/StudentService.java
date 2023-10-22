package com.example.bootlegekool.service;

import com.example.bootlegekool.Student;
import com.example.bootlegekool.exception.*;
import com.example.bootlegekool.repository.StudentRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
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

    //Delete Student
    public void deleteStudent(Long studentId){

        if (!studentRepository.existsById(studentId)){
            throw new StudentNotFoundException("Student with id " + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);

    }
}
