package com.example.bootlegekool;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            ""
    )
    Boolean selectExistsEmail(String email);
}