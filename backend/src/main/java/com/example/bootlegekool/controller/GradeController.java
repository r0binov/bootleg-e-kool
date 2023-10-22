package com.example.bootlegekool.controller;

import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.service.GradeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/ekool/grade")
public class GradeController {
    private final GradeService gradeService;
    @GetMapping(path = "/")
    public List<Grade> getAllGrades(){
        return gradeService.getAllGrades();
    }
    @PostMapping(path = "/addGrade")
    public Grade addGrade(@RequestBody Grade grade){
        return gradeService.addGrade(grade);
    }
    @PutMapping(path = "/updateGrade/{id}")
    public String updateGrade(@PathVariable("id")Long id, Grade updatedGrade) {
        return gradeService.updateGrade(id, updatedGrade);
    }
    @DeleteMapping(path = "/deleteGrade/{id}")
    public String deleteGrade(@PathVariable("id")Long id) {
        return gradeService.deleteGrade(id);
    }
}
