package com.example.bootlegekool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGradeDTO {
    private Long gradeId;
    private int gradeValue;
}
