package com.example.bootlegekool.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "grade")
@NoArgsConstructor
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    @Column(name = "grade")
    private int gradeValue;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Grade(Student student, Subject subject, int gradeValue) {
        this.student = student;
        this.subject = subject;
        this.gradeValue = gradeValue;
    }
}
