package com.example.bootlegekool.controller;

import com.example.bootlegekool.models.Student;
import com.example.bootlegekool.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ekool/student")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;

    //Get a list of all students
    @GetMapping(path = "/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //Adding new students
    @PostMapping(path = "/addStudent")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    //Delete student
    @DeleteMapping(path = "/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

}
