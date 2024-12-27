package com.springboot_jpa_crud.dto;

import lombok.Data;

@Data
public class EmployeeDepartmentDTO {

    private int emp_id;
    private String name;
    private String email;
    private long phone;
    private String address;
    private String city;
    private String state;
}
