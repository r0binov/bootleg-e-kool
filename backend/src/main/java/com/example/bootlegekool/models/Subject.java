package com.example.bootlegekool.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "subject")
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(nullable = false)
    private String subjectName;

    public Subject(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }
}

