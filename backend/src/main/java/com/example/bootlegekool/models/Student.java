package com.example.bootlegekool.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
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

