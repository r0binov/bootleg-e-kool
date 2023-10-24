package com.example.bootlegekool.controller;

import com.example.bootlegekool.models.Subject;
import com.example.bootlegekool.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ekool/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") Long id) {
        Subject subject = subjectService.getSubjectById(id).orElse(null);
        if (subject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping(path = "/addSubject")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    @PutMapping("/updateSubject/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") Long id, @RequestBody Subject subject) {
        if (!subjectService.getSubjectById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subject.setSubjectId(id);
        Subject updatedSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable("id") Long id) {
        if (!subjectService.getSubjectById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
