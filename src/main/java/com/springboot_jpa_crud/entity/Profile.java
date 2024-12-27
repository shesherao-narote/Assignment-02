package com.springboot_jpa_crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profile_id;

    private long phone;
    private String address;
     private String city;
     private String state;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "employee_id")

     @JsonBackReference
     private Employee employee;

//    public Profile(int i, String s) {
//
//    }
}
