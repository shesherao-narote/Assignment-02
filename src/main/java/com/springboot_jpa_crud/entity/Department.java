package com.springboot_jpa_crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    public Department(String newDepartment) {

    }

    public Department() {

    }
}
