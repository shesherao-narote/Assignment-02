package com.springboot_jpa_crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EmployeeDto {

    private int emp_id;
    private String name;
    private String email;
    private String password;
    private boolean isDeleted;

    private List<ProfileDto> profiles;

    private DepartmentDto department;

}
