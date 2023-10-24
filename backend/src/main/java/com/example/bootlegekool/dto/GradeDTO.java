package com.example.bootlegekool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GradeDTO {
    private Long studentId;
    private Long subjectId;
    private int gradeValue;
}
