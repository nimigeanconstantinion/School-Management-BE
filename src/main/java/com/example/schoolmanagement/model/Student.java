package com.example.schoolmanagement.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(
        name = "Student"
)
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String address;
    String email;
    String pass;
    public Student(String name, String address, String email,String pass) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.pass=pass;
    }

}
