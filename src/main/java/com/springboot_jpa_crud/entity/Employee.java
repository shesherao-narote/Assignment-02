package com.springboot_jpa_crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    private String name;

    private String email;

    private String password;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "employee" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Profile> profiles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    private Department department;

    public void addProfile(Profile profile) {
        profiles.add(profile);
        profile.setEmployee(this);
    }

    public void removeProfile(Profile profile) {
        profiles.remove(profile);
        profile.setEmployee(null);
    }

    // Add helper methods for department
    public void assignDepartment(Department department) {
        this.department = department;
        if (department != null) {
            department.setEmployee(this);
        }
    }

    public void removeDepartment() {
        if (this.department != null) {
            this.department.setEmployee(null);
        }
        this.department = null;
    }
}
