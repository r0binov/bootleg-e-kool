package com.example.bootlegekool;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students", schema = "school")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    public Student(String name, String email){
        this.name = name;
        this.email = email;
    }


}
