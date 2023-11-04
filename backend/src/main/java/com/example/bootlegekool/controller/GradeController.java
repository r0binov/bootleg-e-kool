package com.example.bootlegekool.controller;

import com.example.bootlegekool.dto.GradeDTO;
import com.example.bootlegekool.dto.UpdateGradeDTO;
import com.example.bootlegekool.models.Grade;
import com.example.bootlegekool.service.GradeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/ekool/grade")
public class GradeController {
    private final GradeService gradeService;
    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @GetMapping(path = "/")
    public List<Grade> getAllGrades(){
        return gradeService.getAllGrades();
    }
    @PostMapping(path = "/addGrade")
    public Grade addGrade(@RequestBody GradeDTO gradeDTO) {
        return gradeService.addGrade(gradeDTO);
    }
    @PutMapping(path = "/updateGrade")
    public String updateGrade(@RequestBody UpdateGradeDTO updateGradeDTO) {
        return gradeService.updateGrade(updateGradeDTO);
    }
    @DeleteMapping(path = "/deleteGrade/{id}")
    public void deleteGrade(@PathVariable("id")Long id) {
        gradeService.deleteGrade(id);
    }
}
