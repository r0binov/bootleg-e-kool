package com.example.bootlegekool.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    private int grade;

    public Grade(Long gradeId, Student student, Subject subject, int grade) {
        this.gradeId = gradeId;
        this.student = student;
        this.subject = subject;
        this.grade = grade;
    }
}
