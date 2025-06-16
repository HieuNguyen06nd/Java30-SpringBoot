package com.hieunguyen.managestudent.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private Classes classEntity;
}
