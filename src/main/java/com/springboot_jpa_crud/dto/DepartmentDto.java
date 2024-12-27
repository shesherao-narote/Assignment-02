package com.springboot_jpa_crud.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class DepartmentDto {

    private int id;
    private String name;

    @JsonBackReference
    private EmployeeDto employeeDto;
}
