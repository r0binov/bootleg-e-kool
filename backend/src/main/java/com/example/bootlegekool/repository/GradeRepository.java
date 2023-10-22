package com.example.bootlegekool.repository;

import com.example.bootlegekool.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
